package pro.sky.animal_shelter_ji22_team1_app.command;

import org.springframework.stereotype.Component;

@Component
public class HelpCommand implements Command {
    private final Menu menu;

    private HelpCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.help();
    }
}
