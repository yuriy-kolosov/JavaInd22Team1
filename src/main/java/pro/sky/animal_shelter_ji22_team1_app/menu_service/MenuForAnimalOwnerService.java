package pro.sky.animal_shelter_ji22_team1_app.menu_service;

import org.springframework.stereotype.Service;
import pro.sky.animal_shelter_ji22_team1_app.entity.Type;

@Service
@AutoInjectMenuServiceByType(type = Type.ANIMAL_OWNER)
public class MenuForAnimalOwnerService implements MenuService {
    @Override
    public String getGreetingText() {
        return null;
    }
}
