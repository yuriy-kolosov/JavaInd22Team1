package pro.sky.animal_shelter_ji22_team1_app.ClientService;

import org.springframework.stereotype.Service;

@Service
@AutowiredService(type = ClientType.CHOOSING_CLIENT)
public class ChoosingClientService implements ClientService {
    @Override
    public String getGreetingText() {
        return null;
    }
}
