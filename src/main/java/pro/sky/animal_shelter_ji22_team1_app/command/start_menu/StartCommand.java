package pro.sky.animal_shelter_ji22_team1_app.command.start_menu;

import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.command.Command;

@Component
public class StartCommand implements Command {
    private final StartMenu menu;

    private StartCommand(StartMenu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.start();
    }
}
