package pro.sky.animal_shelter_ji22_team1_app.command.daily_report_form_menu;

import org.springframework.stereotype.Component;

@Component
public class DailyReportFormMenu {
    public String dailyReportForm() {
        return """
                Доброго времени суток отправьте пожалуйста отчет при помощи следующих команд:
                /pet_photo - фото животного;
                /pet_diet - рацион животного;
                /pet_general - общее самочувствие животного;
                /pet_behavior - изменение в поведении животного.
                """;
    }
}
