package pro.sky.animal_shelter_ji22_team1_app.command.entry_menu;

import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.command.Command;

@Component
public class DogRulesCommand implements Command {

    private final EntryMenu menu;

    public DogRulesCommand(EntryMenu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.dogRules();
    }

}
