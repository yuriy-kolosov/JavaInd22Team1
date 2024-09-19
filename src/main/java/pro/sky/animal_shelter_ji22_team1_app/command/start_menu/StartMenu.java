package pro.sky.animal_shelter_ji22_team1_app.command.start_menu;

import org.springframework.stereotype.Component;

@Component
public class StartMenu {

    public String start() {
        return "Доброго времени суток, я бот, который помогает животным, оказавшимся в сложной ситуации, " +
                "и людям, которые хотят найти себе нового пушистого друга, обрести то, что они ищут. " +
                "Хотите узнать больше о нас, - введите команду /help";
    }

    public String help() {
        return "Доступны следующие команды:" +
//                                                                          Point 0 commands
                "\n/volunteer - связаться с волонтером;" +
//                                                                          Point 1 commands
                "\n/shelter_info - узнать информацию о приюте;" +
//                                                                          Point 2 commands
                "\n/shelter_entry - получить подробную информацию о том, как взять животное из приюта;" +
//                                                                          Point 3 commands
                "\n/daily_report_form - прислать ежедневный отчет о содержании животного";
//                                                                              ...
    }

    public String volunteer() {
        return """
                 "Сообщаем Вам номер контактного телефона службы волонтеров приюта:
                 8 800 909 90-90
                """;
    }

}
