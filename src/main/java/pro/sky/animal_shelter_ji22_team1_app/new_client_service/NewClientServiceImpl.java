package pro.sky.animal_shelter_ji22_team1_app.new_client_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.sky.animal_shelter_ji22_team1_app.entity.ShelterEntity;
import pro.sky.animal_shelter_ji22_team1_app.entity.Type;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.ShelterDoesNotExistException;
import pro.sky.animal_shelter_ji22_team1_app.exception.UserWithThisLoginAlreadyExistException;
import pro.sky.animal_shelter_ji22_team1_app.exception.UserWithThisPhoneAlreadyExistException;
import pro.sky.animal_shelter_ji22_team1_app.repository.ShelterRepository;
import pro.sky.animal_shelter_ji22_team1_app.repository.UserRepository;

import java.time.LocalDateTime;

/**
 * Класс, имплементирующий интерфейс сервиса NewClient
 *
 * @author yuriy_kolosov
 */

public class NewClientServiceImpl implements NewClientService {

    /**
     * Инжекция репозиториев: Shelter ("Приют") и User ("Клиент")
     */
    public final ShelterRepository shelterRepository;
    private final UserRepository userRepository;

    public NewClientServiceImpl(ShelterRepository shelterRepository, UserRepository userRepository) {
        this.shelterRepository = shelterRepository;
        this.userRepository = userRepository;
    }

    Logger logger = LoggerFactory.getLogger(NewClientServiceImpl.class);

    /**
     * Вывод общей информации о приюте с номером (id) для нового клиента
     *
     * @param id номер приюта
     * @return текст с общей информацией о данном приюте
     */
    @Override
    public String getShelterAboutText(Long id) {
        logger.debug("\"NewClientServiceImpl\" getShelterAboutText method was invoke...");
        ShelterEntity shelter = shelterRepository.findById(id)
                .orElseThrow(() -> new ShelterDoesNotExistException("Приют номер %d в базе данных отсутствует"
                        .formatted(id)));
        return shelter.getRules();
    }

    /**
     * Вывод информации о месте расположения приюта с номером (id) (схемы проезда) для нового клиента
     *
     * @param id номер приюта
     * @return файл .jpeg с информацией об адресе и схемой проезда к данному приюту
     */
    @Override
    public byte[] getShelterLocationScheme(Long id) {
        logger.debug("\"NewClientServiceImpl\" getShelterLocationScheme method was invoke...");
        ShelterEntity shelter = shelterRepository.findById(id)
                .orElseThrow(() -> new ShelterDoesNotExistException("Приют номер %d в базе данных отсутствует"
                        .formatted(id)));
        return shelter.getLocationSchemeData();
    }

    /**
     * Создание блока данных (сущности) для последующей записи в базу информации о новом клиенте
     *
     * @param firstName     имя нового клиента
     * @param secondName    отчество нового клиента
     * @param login         логин нового клиента
     * @param phone         номер телефона нового клиента
     * @param localDateTime дата и время регистрации нового клиента
     * @return данные с информацией о новом клиенте (все обязательные для заполнения поля)
     */
    @Override
    public UserEntity createNewClient(String firstName
            , String secondName
            , String login
            , String phone
            , LocalDateTime localDateTime) {
        logger.debug("\"NewClientServiceImpl\" createNewClient method was invoke...");
        if (userRepository.findByLogin(login) != null) {
            throw new UserWithThisLoginAlreadyExistException("Пользователь с логином %s уже существует"
                    .formatted(login));
        }
        if (userRepository.findByPhone(phone) != null) {
            throw new UserWithThisPhoneAlreadyExistException("Пользователь с номером телефона %s уже существует"
                    .formatted(phone));
        }
        UserEntity user = new UserEntity();
        user.setFirstname(firstName);
        user.setSecondname(secondName);
        user.setLogin(login);
        user.setPhone(phone);
        user.setRegistrationDate(localDateTime);
        return user;
    }

    /**
     * Дополнение блока данных (сущности) для последующей записи в базу информации о новом клиенте
     *
     * @param user     блок данных (сущность) нового клиента с заполненными обязательными полями
     *                 (необязательные поля заполнены частично или не заполнены)
     * @param lastName фамилия нового клиента
     * @return данные с информацией о новом клиенте (все обязательные для заполнения поля плюс фамилия клиента)
     */
    @Override
    public UserEntity addLastNameToNewClient(UserEntity user
            , String lastName) {
        logger.debug("\"NewClientServiceImpl\" addLastNameToNewClient method was invoke...");
        user.setLastname(lastName);
        return user;
    }

    /**
     * Дополнение блока данных (сущности) для последующей записи в базу информации о новом клиенте
     *
     * @param user блок данных (сущность) нового клиента с заполненными обязательными полями
     *             (необязательные поля заполнены частично или не заполнены)
     * @param type клиента
     * @return данные с информацией о новом клиенте (все обязательные для заполнения поля плюс тип клиента)
     */
    @Override
    public UserEntity addTypeToNewClient(UserEntity user
            , Type type) {
        logger.debug("\"NewClientServiceImpl\" addTypeToNewClient method was invoke...");
        user.setType(type);
        return user;
    }

    /**
     * Дополнение блока данных (сущности) для последующей записи в базу информации о новом клиенте
     *
     * @param user    блок данных (сущность) нового клиента с заполненными обязательными полями
     *                (необязательные поля заполнены частично или не заполнены)
     * @param comment комментарий (дополнительная текстовая информация о новом клиенте)
     * @return данные с информацией о новом клиенте (все обязательные для заполнения поля плюс комментарий)
     */
    @Override
    public UserEntity addCommentToNewClient(UserEntity user
            , String comment) {
        logger.debug("\"NewClientServiceImpl\" addCommentToNewClient method was invoke...");
        user.setComment(comment);
        return user;
    }

    /**
     * Дополнение блока данных (сущности) для последующей записи в базу информации о новом клиенте
     *
     * @param user   блок данных (сущность) нового клиента с заполненными обязательными полями
     *               (необязательные поля заполнены частично или не заполнены)
     * @param chatId идентификатор чата с новым клиентом, взаимодействующего с Телеграм-ботом Приюта
     * @return данные с информацией о новом клиенте (все обязательные для заполнения поля плюс идентификатор чата)
     */
    @Override
    public UserEntity addChatIdToNewClient(UserEntity user
            , Long chatId) {
        logger.debug("\"NewClientServiceImpl\" addChatIdToNewClient method was invoke...");
        user.setChatId(chatId);
        return user;
    }

    /**
     * Запись блока данных (сущности) клиента в базу данных Приюта
     *
     * @param user блок данных (сущность) нового клиента с заполненными обязательными полями
     *             (необязательные поля заполнены частично или не заполнены),
     *             занесенный в базу данных Приюта
     */
    @Override
    public void putNewClientToDb(UserEntity user) {
        logger.debug("\"NewClientServiceImpl\" putNewClientToDb method was invoke...");
        if (userRepository.findById(user.getId()).isPresent()) {
            throw new RuntimeException("Клиент с таким номером %s уже присутствует в базе данных"
                    .formatted(user.getId()));
        } else {
            userRepository.save(user);
        }
    }

}
