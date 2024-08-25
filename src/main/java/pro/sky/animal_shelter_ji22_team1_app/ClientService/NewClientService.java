package pro.sky.animal_shelter_ji22_team1_app.ClientService;

import org.springframework.stereotype.Service;

@Service
@AutowiredService(type = ClientType.NEW_CLIENT)
public class NewClientService implements ClientService {
    @Override
    public String getGreetingText() {
        return null;
    }
}
