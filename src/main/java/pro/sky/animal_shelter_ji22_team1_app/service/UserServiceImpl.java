package pro.sky.animal_shelter_ji22_team1_app.service;

import org.springframework.stereotype.Service;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;
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

    /**
     * Поиск клиента приюта по идентификатору
     *
     * @param id
     * @return найденный клиент
     * @throws UserDoesNotExistException если пользователь с указанным идентификатором не был найден
     */
    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserDoesNotExistException("Пользователя с id = %d не существует".formatted(id)));
    }

    /**
     * Поиск всех клиентов приюта
     *
     * @return users
     */
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    /**
     * Создание нового клиента приюта
     *
     * @param user
     * @throws UserWithThisLoginAlreadyExistException если клиент с таким логином уже существует
     * @throws UserWithThisPhoneAlreadyExistException если клиент с таким номером телефона уже существует
     */
    public void save(UserEntity user) {
        if (!userRepository.findByLogin(user.getLogin()).isEmpty()) {
            throw new UserWithThisLoginAlreadyExistException("Пользователь с логином %s уже существует"
                    .formatted(user.getLogin()));
        }
        if (!userRepository.findByPhone(user.getPhone()).isEmpty()) {
            throw new UserWithThisPhoneAlreadyExistException("Пользователь с номером телефона %s уже существует"
                    .formatted(user.getPhone()));
        }
        userRepository.save(user);
    }

    /**
     * Изменение данных клиента приюта
     *
     * @param user
     * @throws UserDoesNotExistException              если клиента с таким идентификатором существует
     * @throws UserWithThisLoginAlreadyExistException если клиент с таким логином уже существует
     * @throws UserWithThisPhoneAlreadyExistException если клиент с таким номером телефона уже существует
     */
    public UserEntity change(UserEntity user) {
        if (findById(user.getId()) == null) {
            throw new UserDoesNotExistException("Пользователя с id = %d не существует".formatted(user.getId()));
        }
        save(user);
        return findById(user.getId());
    }

    /**
     * удаление клиента приюта по идентификатору
     *
     * @param id
     * @throws UserDoesNotExistException если клиент с таким идентификатором не существует
     */
    public void delete(Long id) {
        if (findById(id) == null) {
            throw new UserDoesNotExistException("Пользователя с id = %d не существует".formatted(id));
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity findByChatId(Long chatId) {
        return userRepository.findByChatId(chatId);
    }
}
