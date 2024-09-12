package pro.sky.animal_shelter_ji22_team1_app.telegram_api;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.command.RemoteControl;
import pro.sky.animal_shelter_ji22_team1_app.entity.Type;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.UserDoesNotExistException;
import pro.sky.animal_shelter_ji22_team1_app.repository.UserRepository;
import pro.sky.animal_shelter_ji22_team1_app.user_safer.UserSafer;

import java.time.LocalDateTime;
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

//            if (update.message() != null) {

                userSafer.saveUser(update);

                Long chatId = update.message().chat().id();

                switch (update.message().text()) {
//                                                                                          Menu Command from Point#0
                    case "/start" -> sendMessage(chatId, remoteControl.start());
                    case "/help" -> sendMessage(chatId, remoteControl.help());
//                                                                                                      ...
//                                                                                          Menu Command from Point#1
                    case "/shelter_info" -> sendMessage(chatId, remoteControl.shelterInfo());
//                                                                                                      ...
//                                                                                          Menu Command from Point#2
                    case "/entry" -> sendMessage(chatId, remoteControl.entry());
                    case "/pets" -> sendMessage(chatId, remoteControl.pets());
                    case "/rules" -> sendMessage(chatId, remoteControl.rules());
//                                                                                                      ...
//                                                                                          Menu Command from Point#3
                    case "/dailyreportform" -> sendMessage(chatId, remoteControl.dailyReportForm());
//                                                                                      ...
                    case "/location" -> sendMessage(chatId, remoteControl.location());

                    case "/shelter_contacts" -> sendMessage(chatId, remoteControl.shelterContacts());

                    case "/health_and_safety" -> sendMessage(chatId, remoteControl.heathAndSafety());

                    case "/client_contacts" -> sendMessage(chatId, remoteControl.clientContacts());
//                                                                                          No such command
                    default -> sendMessage(chatId, "такой команды не существует. Вы можете вызвать справку" +
                            " командой /help");
                }
//            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        SendResponse response = telegramBot.execute(message);
    }
}
