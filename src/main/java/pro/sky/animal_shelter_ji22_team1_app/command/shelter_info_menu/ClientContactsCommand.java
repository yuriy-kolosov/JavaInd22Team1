package pro.sky.animal_shelter_ji22_team1_app.command.shelter_info_menu;

import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.command.Command;
@Component
public class ClientContactsCommand implements Command {
    private final ShelterInfoMenu menu;

    public ClientContactsCommand(ShelterInfoMenu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.clientContacts();
    }
}
