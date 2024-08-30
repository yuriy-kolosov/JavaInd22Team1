package pro.sky.animal_shelter_ji22_team1_app.telegram_api;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import pro.sky.animal_shelter_ji22_team1_app.ClientService.ClientService;

import java.util.List;

public class TelegramBotUpdatesListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final UserEvaluator evaluator;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, UserEvaluator evaluator) {
        this.telegramBot = telegramBot;
        this.evaluator = evaluator;
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            Integer chatId = Math.toIntExact(update.message().chat().id());
            ClientService clientService = evaluator.evaluate(chatId);
            String textMessage = clientService.getGreetingText();
            SendMessage message = new SendMessage(chatId, textMessage);
            SendResponse response = telegramBot.execute(message);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
