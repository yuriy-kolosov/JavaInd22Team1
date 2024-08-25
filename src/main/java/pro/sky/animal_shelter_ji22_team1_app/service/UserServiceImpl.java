package pro.sky.animal_shelter_ji22_team1_app.service;

import org.springframework.stereotype.Service;
import pro.sky.animal_shelter_ji22_team1_app.exception.UserDoesNotExistException;
import pro.sky.animal_shelter_ji22_team1_app.exception.UserWithThisLoginAlreadyExistException;
import pro.sky.animal_shelter_ji22_team1_app.exception.UserWithThisPhoneAlreadyExistException;
import pro.sky.animal_shelter_ji22_team1_app.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserDoesNotExistException("Пользователя с id = %d не существует".formatted(id)));
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public void save(UserEntity user) {
        if (userRepository.findByLogin(user.getLogin) != null) {
            throw new UserWithThisLoginAlreadyExistException("Пользователь с логином %s уже существует"
                    .formatted(user.getLogin()));
        }
        if (userRepository.findByPhone(user.getPhone()) != null) {
            throw new UserWithThisPhoneAlreadyExistException("Пользователь с номером телефона %s уже существует"
                    .formatted(user.getPhone()));
        }
        userRepository.save(user);
    }

    public UserEntity change(UserEntity user) {
        if (findById(user.geId) == null) {
            throw new UserDoesNotExistException("Пользователя с id = %d не существует".formatted(user.getId));
        }
        save(user);
        return findById(user.getId());
    }

    public void delete(Long id) {
        if (findById(id) == null) {
            throw new UserDoesNotExistException("Пользователя с id = %d не существует".formatted(id));
        }
        delete(id);
    }
}
