package pro.sky.animal_shelter_ji22_team1_app.command.daily_report_form_menu;

import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.command.Command;;

@Component
public class DailyReportFormCommand implements Command {
    private final DailyReportFormMenu menu;

    private DailyReportFormCommand(DailyReportFormMenu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.dailyReportForm();
    }
}
