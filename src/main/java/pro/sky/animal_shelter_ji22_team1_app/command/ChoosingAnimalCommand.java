package pro.sky.animal_shelter_ji22_team1_app.command;

import org.springframework.stereotype.Component;

@Component
public class ChoosingAnimalCommand implements Command {

    private final Menu menu;

    private ChoosingAnimalCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.choosingAnimal();
    }
}
