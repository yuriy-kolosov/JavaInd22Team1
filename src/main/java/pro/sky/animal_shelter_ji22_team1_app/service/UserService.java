package pro.sky.animal_shelter_ji22_team1_app.service;

import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;

import java.util.Collection;

public interface UserService {
    Collection<UserEntity> findAll();

    UserEntity findById(Long userId);

    void save(UserEntity user);

    UserEntity change(UserEntity user);

    void delete(Long userId);

    UserEntity findByChatId(Long chatId);
}
