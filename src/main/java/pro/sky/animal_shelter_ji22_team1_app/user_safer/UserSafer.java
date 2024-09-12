package pro.sky.animal_shelter_ji22_team1_app.user_safer;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import org.springframework.stereotype.Service;
import pro.sky.animal_shelter_ji22_team1_app.entity.Type;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;
import pro.sky.animal_shelter_ji22_team1_app.repository.UserRepository;

import java.time.LocalDateTime;

@Service
public class UserSafer {
    private final UserRepository userRepository;

    public UserSafer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(Update update) {
        if (userRepository.findByChatId(update.message().chat().id()) != null) {
            return;
        }

        User user = update.message().from();
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstname(user.firstName());
        userEntity.setLastname(user.lastName());
        userEntity.setChatId(update.message().chat().id());
        userEntity.setRegistrationDate(LocalDateTime.now());
        userEntity.setType(Type.NEW_CLIENT);
        userEntity.setLogin(user.username());

        userEntity.setSurname("asdgasdg");
        userEntity.setPhone("asdgasg");

        userRepository.save(userEntity);
    }
}
