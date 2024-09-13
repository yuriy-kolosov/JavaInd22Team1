package pro.sky.animal_shelter_ji22_team1_app.telegram_api;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.command.RemoteControl;
import pro.sky.animal_shelter_ji22_team1_app.user_safer.UserSafer;

import java.util.List;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final RemoteControl remoteControl;


    private final UserSafer userSafer;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, RemoteControl remoteControl,
                                      UserSafer userSafer) {
        this.telegramBot = telegramBot;
        this.remoteControl = remoteControl;
        this.userSafer = userSafer;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {

            if (update.message() != null) {

                userSafer.saveUser(update);

                Long chatId = update.message().chat().id();

                switch (update.message().text()) {

                    case "/start" -> sendMessage(chatId, remoteControl.start());
                    case "/help" -> sendMessage(chatId, remoteControl.help());
//                                                                                          Menu Command from Point#1
                    case "/shelter_info" -> sendMessage(chatId, remoteControl.shelterInfo());
//                                                                                          Menu Command from Point#2
                    case "/entry" -> sendMessage(chatId, remoteControl.entry());
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
                    case "/daily_report_form" -> sendMessage(chatId, remoteControl.dailyReportForm());
//                                                                                      ...
                    case "/location" -> sendMessage(chatId, remoteControl.location());

                    case "/shelter_contacts" -> sendMessage(chatId, remoteControl.shelterContacts());

                    case "/health_and_safety" -> sendMessage(chatId, remoteControl.heathAndSafety());

                    case "/client_contacts" -> sendMessage(chatId, remoteControl.clientContacts());
                    case "/firstname" -> sendMessage(chatId, "Введите имя в формате: firstname Имя");
                    case "/surname" -> sendMessage(chatId, "Введите отчество в формате: surname Отчество");
                    case "/lastname" -> sendMessage(chatId, "Введите фамилию в формате: lastname Фамилия");
                    case "/phone" -> sendMessage(chatId,
                            "Введите номер контактного телефона в формате: +7 999 999 99-99");
//                                                                                          No such command
                    default -> {
                        if (update.message().text().matches("firstname\\s\\w+")) {
                            userSafer.safeFirstname(update);
                            sendMessage(chatId, "имя сохранено");
                        } else if (update.message().text().matches("surname\\s\\w+")) {
                            userSafer.safeSurname(update);
                            sendMessage(chatId, "имя сохранено");
                        } else if (update.message().text().matches("lastname\\s\\w+")) {
                            userSafer.safeLastname(update);
                            sendMessage(chatId, "имя сохранено");
                        } else if (update.message().text().matches("\\+7\\s\\d{3}\\s\\d{3}\\s\\d{2}-\\d{2}")) {
                            userSafer.safePhone(update);
                            sendMessage(chatId, "имя сохранено");
                        } else {
                            sendMessage(chatId, "такой команды не существует. Вы можете вызвать справку" +
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

}
