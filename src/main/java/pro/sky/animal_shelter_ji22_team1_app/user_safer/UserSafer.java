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

    public void safeFirstname(Update update){
        UserEntity user = findUser(update);

        user.setFirstname(update.message().text().substring(10));
        userRepository.save(user);
    }

    public void safeSurname(Update update){
        UserEntity user = findUser(update);

        user.setSurname(update.message().text().substring(7));
        userRepository.save(user);
    }

    public void safeLastname(Update update){
        UserEntity user = findUser(update);

        user.setLastname(update.message().text().substring(9));
        userRepository.save(user);
    }

    public void safePhone(Update update){
        UserEntity user = findUser(update);

        user.setPhone(update.message().text());
        userRepository.save(user);
    }

    private UserEntity findUser(Update update){
        Long chatId = update.message().chat().id();

        return userRepository.findByChatId(chatId);
    }
}
