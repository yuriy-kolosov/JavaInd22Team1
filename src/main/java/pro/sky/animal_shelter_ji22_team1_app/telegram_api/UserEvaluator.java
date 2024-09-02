package pro.sky.animal_shelter_ji22_team1_app.telegram_api;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.menu_service.AutoInjectMenuServiceByType;
import pro.sky.animal_shelter_ji22_team1_app.menu_service.MenuService;

import pro.sky.animal_shelter_ji22_team1_app.entity.Type;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;

import pro.sky.animal_shelter_ji22_team1_app.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserEvaluator {
    private final UserService userService;
    @Autowired
    private List<MenuService> clientServices;

    private Map<Type, MenuService> serviceMap = new HashMap<>();

    public UserEvaluator(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        clientServices.forEach(service -> {
            serviceMap.put(service.getClass()
                    .getAnnotation(AutoInjectMenuServiceByType.class).type(), service);
        });
    }


    public MenuService evaluate(Long chatId) {
        UserEntity user = userService.findByChatId(chatId);
        Type type;
        if (user == null) {
           type=Type.NEW_CLIENT;
        } else {
            type = user.getType();
        }
        return serviceMap.get(type);
    }

}
