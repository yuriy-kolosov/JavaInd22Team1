package pro.sky.animal_shelter_ji22_team1_app.telegram_api;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.command.RemoteControl;
import pro.sky.animal_shelter_ji22_team1_app.report_safer.ReportSafer;
import pro.sky.animal_shelter_ji22_team1_app.user_safer.UserSafer;

import java.io.*;
import java.net.URL;
import java.util.List;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final RemoteControl remoteControl;
    private final UserSafer userSafer;
    private final ReportSafer reportSafer;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, RemoteControl remoteControl,
                                      UserSafer userSafer, ReportSafer reportSafer) {
        this.telegramBot = telegramBot;
        this.remoteControl = remoteControl;
        this.userSafer = userSafer;
        this.reportSafer = reportSafer;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {

            Long chatId = update.message().chat().id();

            if (update.message().photo() != null) {
                PhotoSize[] photoSizes = update.message().photo();
                PhotoSize photoSize = photoSizes[0];
                String fileId = photoSize.fileId();

                GetFile request = new GetFile(fileId);
                GetFileResponse getFileResponse = telegramBot.execute(request);

                File file = getFileResponse.file();

                String fullPath = telegramBot.getFullFilePath(file);
                URL url = null;
                try {
                    url = new URL(fullPath);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                try (BufferedInputStream bis = new BufferedInputStream(url.openStream());) {
                    reportSafer.SafePhoto(update, bis.readAllBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


            if (update.message().text() != null) {

                userSafer.saveUser(update);

                switch (update.message().text()) {

                    case "/start" -> sendMessage(chatId, remoteControl.start());
                    case "/help" -> sendMessage(chatId, remoteControl.help());
                    case "/volunteer" -> sendMessage(chatId, remoteControl.help());
//                                                                                          Menu Command from Point#1
                    case "/shelter_info" -> sendMessage(chatId, remoteControl.shelterInfo());
//                                                                                          Menu Command from Point#2
                    case "/shelter_entry" -> sendMessage(chatId, remoteControl.entry());
//                                                                                          for choosing types of animals
                    case "/dog" -> sendMessage(chatId, remoteControl.dog());
                    case "/cat" -> sendMessage(chatId, remoteControl.cat());
//                                                                                          list of dogs
                    case "/dogs" -> sendMessage(chatId, remoteControl.dogs());
//                                                                                          list for cats
                    case "/cats" -> sendMessage(chatId, remoteControl.cats());
//                                                                                          for all types of animals
                    case "/documents" -> sendMessage(chatId, remoteControl.documents());
                    case "/transportation" -> sendMessage(chatId, remoteControl.transportation());
                    case "/pet_house" -> sendMessage(chatId, remoteControl.petHouse());
                    case "/invalid_pet_house" -> sendMessage(chatId, remoteControl.invalidPetHouse());
                    case "/waiver_list" -> sendMessage(chatId, remoteControl.waiverList());
//                                                                                          for dogs
                    case "/dog_rules" -> sendMessage(chatId, remoteControl.dogRules());
                    case "/puppy_house" -> sendMessage(chatId, remoteControl.puppyHouse());
                    case "/dog_handler" -> sendMessage(chatId, remoteControl.dogHandler());
                    case "/dog_handler_list" -> sendMessage(chatId, remoteControl.dogHandlerList());
//                                                                                          for cats
                    case "/cat_rules" -> sendMessage(chatId, remoteControl.catRules());
//                                                                                          Menu Command from Point#3
                    case "/daily_report_form" -> {
                        sendMessage(chatId, remoteControl.dailyReportForm());
                        reportSafer.saveReport(update);
                    }
//                                                                                      ...
                    case "/shelter_rules" -> sendMessage(chatId, remoteControl.shelterRules());
                    case "/address_dogs" -> sendMessage(chatId, remoteControl.addressDogs());
                    case "/address_cats" -> sendMessage(chatId, remoteControl.addressCats());
//
                    case "/location_dogs" -> sendImage(chatId, remoteControl.locationDogs());
                    case "/location_cats" -> sendImage(chatId, remoteControl.locationCats());

                    case "/schedule_dogs" -> sendMessage(chatId, remoteControl.schedulerDogs());
                    case "/schedule_cats" -> sendMessage(chatId, remoteControl.schedulerCats());

                    case "/shelter_dogs_contacts" -> sendMessage(chatId, remoteControl.shelterDogsContacts());
                    case "/shelter_cats_contacts" -> sendMessage(chatId, remoteControl.shelterCatsContacts());

                    case "/health_and_safety" -> sendMessage(chatId, remoteControl.heathAndSafety());

                    case "/client_contacts" -> sendMessage(chatId, remoteControl.clientContacts());

                    case "/firstname" -> sendMessage(chatId, "Введите имя в формате: firstname Имя");
                    case "/surname" -> sendMessage(chatId, "Введите отчество в формате: surname Отчество");
                    case "/lastname" -> sendMessage(chatId, "Введите фамилию в формате: lastname Фамилия");
                    case "/phone" -> sendMessage(chatId,
                            "Введите номер контактного телефона в формате: +7 999 999 99-99");

                    case "/pet_photo" -> sendMessage(chatId, "Отправьте фото питомца");
                    case "/pet_diet" -> sendMessage(chatId, "Отправьте рацион питомца в формате: diet ________");
                    case "/pet_general" -> sendMessage(chatId, "Отправьте информацию о общем самочувствии" +
                                                               " питомца в формате: general ________");
                    case "/pet_behavior" -> sendMessage(chatId, "Отправьте информацию об изменениях в " +
                                                                "поведении питомца в формата: behavior ______________");
//                                                                                          No such command
                    default -> {
                        if (update.message().text().matches("firstname\\s.+")) {
                            userSafer.safeFirstname(update);
                            sendMessage(chatId, "Принято");
                        } else if (update.message().text().matches("surname\\s.+")) {
                            userSafer.safeSurname(update);
                            sendMessage(chatId, "Принято");
                        } else if (update.message().text().matches("lastname\\s.+")) {
                            userSafer.safeLastname(update);
                            sendMessage(chatId, "Принято");
                        } else if (update.message().text().matches("\\+7\\s\\d{3}\\s\\d{3}\\s\\d{2}-\\d{2}")) {
                            userSafer.safePhone(update);
                            sendMessage(chatId, "Принято");
                        } else if (update.message().text().matches("diet\\s.+")) {
                            reportSafer.saveDiet(update);
                            sendMessage(chatId, "Принято");
                        } else if (update.message().text().matches("general\\s.+")) {
                            reportSafer.saveGeneral(update);
                            sendMessage(chatId, "Принято");
                        } else if (update.message().text().matches("behavior\\s.+")) {
                            reportSafer.saveBehavior(update);
                            sendMessage(chatId, "Принято");
                        } else {
                            sendMessage(chatId, "Такой команды не существует. Вы можете вызвать справку" +
                                                " командой /help");
                        }
                    }
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        SendResponse response = telegramBot.execute(message);
    }

    private void sendImage(Long chatId, byte[] location) {
        SendPhoto sendPhoto = new SendPhoto(chatId, location);
        SendResponse response = telegramBot.execute(sendPhoto);
    }

}
