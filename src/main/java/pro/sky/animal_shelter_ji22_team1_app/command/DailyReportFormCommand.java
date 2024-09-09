package pro.sky.animal_shelter_ji22_team1_app.command;

import org.springframework.stereotype.Component;

@Component
public class DailyReportFormCommand implements Command {
    private final Menu menu;

    private DailyReportFormCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.dailyReportForm();
    }
}
