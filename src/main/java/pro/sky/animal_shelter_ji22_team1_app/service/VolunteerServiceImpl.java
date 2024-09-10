package pro.sky.animal_shelter_ji22_team1_app.service;

import org.springframework.stereotype.Service;
import pro.sky.animal_shelter_ji22_team1_app.entity.VolunteerEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.VolunteerDoesNotExistException;
import pro.sky.animal_shelter_ji22_team1_app.exception.VolunteerWithThisLoginAlreadyExistException;
import pro.sky.animal_shelter_ji22_team1_app.exception.VolunteerWithThisPhoneAlreadyExistException;
import pro.sky.animal_shelter_ji22_team1_app.repository.VolunteerRepository;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    private final VolunteerRepository volunteerRepository;

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    /**
     * Поиск волонтёра приюта по идентификатору
     *
     * @param id
     * @return найденный волонтёр
     * @throws VolunteerDoesNotExistException если волонтёр с указанным идентификатором не был найден
     */
    public VolunteerEntity findById(Long id) {
        return volunteerRepository.findById(id)
                .orElseThrow(() ->
                        new VolunteerDoesNotExistException("волонтёра с id = %d не существует".formatted(id)));
    }

    /**
     * Поиск всех волонтёров приюта
     *
     * @return volunteers
     */
    public List<VolunteerEntity> findAll() {
        return volunteerRepository.findAll();
    }

    /**
     * Создание нового волонтёра приюта
     *
     * @param volunteer
     * @throws VolunteerWithThisLoginAlreadyExistException если волонтёр с таким логином уже существует
     * @throws VolunteerWithThisPhoneAlreadyExistException если волонтёр с таким номером телефона уже существует
     */
    public void save(VolunteerEntity volunteer) {
        if (!volunteerRepository.findByLogin(volunteer.getLogin()).isEmpty()) {
            throw new VolunteerWithThisLoginAlreadyExistException("Волонтёр с логином %s уже существует"
                    .formatted(volunteer.getLogin()));
        }
        if (!volunteerRepository.findByPhone(volunteer.getPhone()).isEmpty()) {
            throw new VolunteerWithThisPhoneAlreadyExistException("Волонтёр с номером телефона %s уже существует"
                    .formatted(volunteer.getPhone()));
        }
        volunteerRepository.save(volunteer);
    }

    /**
     * Изменение данных волонтёра приюта
     *
     * @param volunteer
     * @throws VolunteerDoesNotExistException              если волонтёра с таким идентификатором существует
     * @throws VolunteerWithThisLoginAlreadyExistException если волонтёр с таким логином уже существует
     * @throws VolunteerWithThisPhoneAlreadyExistException если волонтёр с таким номером телефона уже существует
     */
    public VolunteerEntity change(VolunteerEntity volunteer) {
        if (findById(volunteer.getId()) == null) {
            throw new VolunteerDoesNotExistException("Волонтёр с id = %d не существует".formatted(volunteer.getId()));
        }
        save(volunteer);
        return findById(volunteer.getId());
    }

    /**
     * удаление волонтёра приюта по идентификатору
     *
     * @param id
     * @throws VolunteerDoesNotExistException если волонтёр с таким идентификатором не существует
     */
    public void delete(Long id) {
        if (findById(id) == null) {
            throw new VolunteerDoesNotExistException("Волонтёра с id = %d не существует".formatted(id));
        }
        volunteerRepository.deleteById(id);
    }

    @Override
    public VolunteerEntity findByChatId(Long chatId) {
        return volunteerRepository.findByChatId(chatId);
    }
}
