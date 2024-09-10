package pro.sky.animal_shelter_ji22_team1_app.command;

import org.springframework.stereotype.Component;

@Component
public class PetsCommand implements Command{

    private final Menu menu;

    public PetsCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.pets();
    }
}
