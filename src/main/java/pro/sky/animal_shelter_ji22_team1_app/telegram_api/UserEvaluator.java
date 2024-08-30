package pro.sky.animal_shelter_ji22_team1_app.telegram_api;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.ClientService.AutowiredService;
import pro.sky.animal_shelter_ji22_team1_app.ClientService.ClientService;
import pro.sky.animal_shelter_ji22_team1_app.ClientService.ClientType;
import pro.sky.animal_shelter_ji22_team1_app.ClientService.NewClientService;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;
import pro.sky.animal_shelter_ji22_team1_app.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserEvaluator {
    private final UserService userService;
    @Autowired
    private List<ClientService> clientServices;

    private Map<ClientType, ClientService> serviceMap = new HashMap<>();

    public UserEvaluator(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init(){
        clientServices.forEach(service -> {
            serviceMap.put(service.getClass()
                    .getAnnotation(AutowiredService.class).type(), service);
        });
    }

    public ClientService evaluate(Integer chatId){
        UserEntity user  =  userService.findByChatId(chatId);
        ClientService type = new NewClientService();
//        String type = user.getType();
        return serviceMap.put(NEW_CLIENT, type);
    }
}
