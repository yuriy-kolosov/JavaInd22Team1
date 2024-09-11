package pro.sky.animal_shelter_ji22_team1_app.command.shelter_info_menu;

import pro.sky.animal_shelter_ji22_team1_app.command.Command;

public class ShelterContactsCommand implements Command {
    private final ShelterInfoMenu menu;

    public ShelterContactsCommand(ShelterInfoMenu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.shelterContacts();
    }
}
