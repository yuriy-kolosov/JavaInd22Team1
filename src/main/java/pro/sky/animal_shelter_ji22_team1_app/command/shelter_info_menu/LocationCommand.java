package pro.sky.animal_shelter_ji22_team1_app.command.shelter_info_menu;

import pro.sky.animal_shelter_ji22_team1_app.command.Command;

public class LocationCommand implements Command {
    private final ShelterInfoMenu menu;

    public LocationCommand(ShelterInfoMenu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.location();
    }
}
