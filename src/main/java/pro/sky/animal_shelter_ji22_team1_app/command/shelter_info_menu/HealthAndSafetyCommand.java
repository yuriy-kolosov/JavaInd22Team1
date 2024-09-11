package pro.sky.animal_shelter_ji22_team1_app.command.shelter_info_menu;

import pro.sky.animal_shelter_ji22_team1_app.command.Command;

public class HealthAndSafetyCommand implements Command {
    private final ShelterInfoMenu menu;

    public HealthAndSafetyCommand(ShelterInfoMenu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.healthAndSafety();
    }
}
