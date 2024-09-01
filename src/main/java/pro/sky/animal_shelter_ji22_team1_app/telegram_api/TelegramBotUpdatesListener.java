package pro.sky.animal_shelter_ji22_team1_app.telegram_api;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.menu_service.MenuService;

import java.util.List;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final UserEvaluator evaluator;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, UserEvaluator evaluator) {
        this.telegramBot = telegramBot;
        this.evaluator = evaluator;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            if (update.message().text().equals("/start")) {
                Integer chatId = Math.toIntExact(update.message().chat().id());

                sendGreetingMessage(chatId);

                sendMessageFromMenuService(chatId);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendGreetingMessage(Integer chatId) {

        String greetingText = "Доброго времени суток, я бот который помогает животным, оказавшимся в сложной ситуации, и людям, " +
                              "которые хотят найти себе нового пушистого друга, обрести то, что они ищут";

        SendMessage greetingMessage = new SendMessage(chatId, greetingText);
        SendResponse greetingResponse = telegramBot.execute(greetingMessage);
    }

    private void sendMessageFromMenuService(Integer chatId){
        MenuService clientService = evaluator.evaluate(chatId);
        String textMessage = clientService.getGreetingText();
        SendMessage message = new SendMessage(chatId, textMessage);
        SendResponse response = telegramBot.execute(message);
    }
}
