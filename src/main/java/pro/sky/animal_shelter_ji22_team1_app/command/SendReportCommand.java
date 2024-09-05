package pro.sky.animal_shelter_ji22_team1_app.command;

import org.springframework.stereotype.Component;

@Component
public class SendReportCommand implements Command {
    private final Menu menu;

    private SendReportCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.sendReport();
    }
}
