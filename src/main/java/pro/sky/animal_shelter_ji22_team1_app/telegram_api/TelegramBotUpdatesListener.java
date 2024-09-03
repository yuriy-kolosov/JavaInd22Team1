package pro.sky.animal_shelter_ji22_team1_app.telegram_api;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetChatResponse;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.entity.Type;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;
import pro.sky.animal_shelter_ji22_team1_app.menu_service.MenuService;
import pro.sky.animal_shelter_ji22_team1_app.repository.UserRepository;

import java.util.List;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final UserEvaluator evaluator;

    private final UserRepository userRepository;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, UserEvaluator evaluator, UserRepository userRepository) {
        this.telegramBot = telegramBot;
        this.evaluator = evaluator;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            if (update.message().text().equals("/start")) {
                Long chatId = update.message().chat().id();

                sendGreetingMessage(chatId);

            }
            if (update.message().text().equals("/help")) {
                Long chatId = update.message().chat().id();

                sendHelpInformation(chatId);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendGreetingMessage(Long chatId) {

        String greetingText = "Доброго времени суток, я бот который помогает животным, оказавшимся в сложной ситуации, " +
                              "и людям, которые хотят найти себе нового пушистого друга, обрести то, что они ищут. " +
                              "Хочешь узнать больше о нас введи команду /help";

        SendMessage greetingMessage = new SendMessage(chatId, greetingText);
        SendResponse greetingResponse = telegramBot.execute(greetingMessage);
    }

    private void sendHelpInformation(Long chatId) {
        StringBuilder infoTextBuilder = new StringBuilder("Доступны следующие команды:");
        infoTextBuilder.append("\n/shelter - узнать информацию о приюте;");
        UserEntity user = userRepository.findByChatId(chatId);
        if (user == null) {
            infoTextBuilder.append("\n/volunteer - связаться с волонтером;");
            String infoText = infoTextBuilder.toString();
            SendMessage infoMessage = new SendMessage(chatId, infoText);
            SendResponse menuResponse = telegramBot.execute(infoMessage);
            return;
        }
        if (user.getType().equals(Type.CHOOSING_CLIENT)) {
            infoTextBuilder.append("\n/choosing_animal - узнать как взять животное из приюта");
        }
        if (user.getType().equals(Type.ANIMAL_OWNER)) {
            infoTextBuilder.append("\n/send_report - прислать отчет;");
        }
        infoTextBuilder.append("\n/volunteer - связаться с волонтером;");
//        String infoText = """
//                Доступны следующие команды:
//                /shelter - узнать информацию о приюте;
//                /choosing_animal - узнать как взять животное из приюта;
//                /send_report - прислать отчет;
//                /volunteer - связаться с волонтером;
//                """;
        String infoText = infoTextBuilder.toString();
        SendMessage infoMessage = new SendMessage(chatId, infoText);
        SendResponse menuResponse = telegramBot.execute(infoMessage);
    }

    private void sendMessageFromMenuService(Long chatId) {
        MenuService clientService = evaluator.evaluate(chatId);
        String textMessage = clientService.getGreetingText();
        SendMessage message = new SendMessage(chatId, textMessage);
        SendResponse response = telegramBot.execute(message);
    }
}
