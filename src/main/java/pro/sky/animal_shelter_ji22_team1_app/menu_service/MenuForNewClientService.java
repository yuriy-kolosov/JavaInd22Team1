package pro.sky.animal_shelter_ji22_team1_app.menu_service;

import org.springframework.stereotype.Service;
import pro.sky.animal_shelter_ji22_team1_app.entity.Type;

@Service
@AutoInjectMenuServiceByType(type = Type.NEW_CLIENT)
public class MenuForNewClientService implements MenuService {
    @Override
    public String getGreetingText() {
        return null;
    }
}
