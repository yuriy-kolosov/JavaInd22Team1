package pro.sky.animal_shelter_ji22_team1_app.command;

import org.springframework.stereotype.Component;

@Component
public class Menu {
    public String start() {
        return "Доброго времени суток, я бот, который помогает животным, оказавшимся в сложной ситуации, " +
               "и людям, которые хотят найти себе нового пушистого друга, обрести то, что они ищут. " +
               "Хочешь узнать больше о нас введи команду /help";
    }

    public String help() {
        return  "Доступны следующие команды:" + "\n/shelter - узнать информацию о приюте;" +
                                 "\n/choosing_animal - узнать как взять животное из приюта" +
                                 "\n/send_report - прислать отчет;" +
                                 "\n/volunteer - связаться с волонтером;";
    }

    public String shelter() {
        return "информация о питомнике";
    }

    public String choosingAnimal() {
        return "нформация о животных";
    }

    public String sendReport() {
        return "информация об отчетах";
    }
}
