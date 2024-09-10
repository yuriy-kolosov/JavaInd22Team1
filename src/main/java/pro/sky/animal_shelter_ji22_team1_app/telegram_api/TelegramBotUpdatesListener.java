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
import pro.sky.animal_shelter_ji22_team1_app.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final RemoteControl remoteControl;
    private final UserRepository userRepository;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, RemoteControl remoteControl,
                                      UserRepository userRepository) {
        this.telegramBot = telegramBot;
        this.remoteControl = remoteControl;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {

            saveUser(update);

            Long chatId = update.message().chat().id();

            switch (update.message().text()) {
//                                                                                          Menu Command from Point#0
                case "/start" -> sendMessage(chatId, remoteControl.start());
                case "/help" -> sendMessage(chatId, remoteControl.help());
//                                                                                                      ...
//                                                                                          Menu Command from Point#1
                case "/address" -> sendMessage(chatId, remoteControl.address());
//                                                                                                      ...
//                                                                                          Menu Command from Point#2
                case "/entry" -> sendMessage(chatId, remoteControl.entry());
                case "/pets" -> sendMessage(chatId, remoteControl.pets());
                case "/rules" -> sendMessage(chatId, remoteControl.rules());
//                                                                                                      ...
//                                                                                          Menu Command from Point#3
                case "/dailyreportform" -> sendMessage(chatId, remoteControl.dailyReportForm());
//                                                                                      ...
//                                                                                          No such command
                default -> sendMessage(chatId, "такой команды не существует. Вы можете вызвать справку" +
                                               " командой /help");
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        SendResponse response = telegramBot.execute(message);
    }

    private void saveUser(Update update) {
        if (userRepository.findByChatId(update.message().chat().id()) != null) {
            return;
        }

        User user = update.message().from();
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstname(user.firstName());
        userEntity.setLastname(user.lastName());
        userEntity.setChatId(update.message().chat().id());
        userEntity.setRegistrationDate(LocalDateTime.now());
        userEntity.setType(Type.NEW_CLIENT);
        userEntity.setLogin(user.username());

        userEntity.setSurname("asdgasdg");
        userEntity.setPhone("asdgasg");

        userRepository.save(userEntity);
    }

}
