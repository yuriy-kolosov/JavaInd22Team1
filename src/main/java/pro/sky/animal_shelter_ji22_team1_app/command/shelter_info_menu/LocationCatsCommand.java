package pro.sky.animal_shelter_ji22_team1_app.command.shelter_info_menu;

import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.command.CommandImage;

@Component
public class LocationCatsCommand implements CommandImage {

    private final ShelterInfoMenu menu;

    public LocationCatsCommand(ShelterInfoMenu menu) {
        this.menu = menu;
    }

    @Override
    public byte[] executeImage() {
        return menu.locationCats();
    }

}
