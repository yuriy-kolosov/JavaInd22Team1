package pro.sky.animal_shelter_ji22_team1_app.command;

import org.springframework.stereotype.Component;

@Component
public class ShelterCommand implements Command {

    private final Menu menu;

    private ShelterCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.shelter();
    }
}
