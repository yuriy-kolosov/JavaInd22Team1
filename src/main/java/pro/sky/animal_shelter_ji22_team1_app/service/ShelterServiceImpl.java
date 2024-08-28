package pro.sky.animal_shelter_ji22_team1_app.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.animal_shelter_ji22_team1_app.entity.ShelterEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.ShelterDoesNotExistException;
import pro.sky.animal_shelter_ji22_team1_app.repository.ShelterRepository;

import java.io.IOException;
import java.util.List;

/**
 * Класс, имплементирующий интерфейс сервиса Shelter
 *
 * @author yuriy_kolosov
 */
@Service
public class ShelterServiceImpl implements ShelterService {

    /**
     * Инжекция репозитория Shelter ("Приют")
     */

    public final ShelterRepository shelterRepository;

    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    /**
     * Вывод информации о всех приютах из базы данных
     * Используется метод репозитория {@link JpaRepository#findAll()}
     *
     * @return информация со списком приютов из базы данных
     */
    @Override
    public List<ShelterEntity> findAllShelters() {
        return shelterRepository.findAll();
    }

    /**
     * Вывод информации о приюте из базы данных по его номеру (id)
     * Используется метод репозитория {@link JpaRepository#findById(Object)}
     */
    @Override
    public ShelterEntity findShelterById(Long id) {
        return shelterRepository.findById(id)
                .orElseThrow(() -> new ShelterDoesNotExistException("Приют номер %d в базе данных отсутствует"
                        .formatted(id)));
    }

    /**
     * Запись информации о приюте в базу данных
     * Используется метод репозитория {@link JpaRepository#save(Object)}
     *
     * @param shelter                   содержит информацию о приюте
     * @param shelterLocationSchemeFile содержит файл .jpeg со схемой проезда к приюту
     * @return информация о новом приюте
     * @throws IOException выбрасывается в случае ошибки ввода-вывода данных при чтении-записи файла
     */
    @Override
    public ShelterEntity saveShelter(ShelterEntity shelter, MultipartFile shelterLocationSchemeFile) throws IOException {
        if (shelterRepository.findById(shelter.getId()).isPresent()) {
            throw new RuntimeException("Приют номер %s уже присутствует в базе данных"
                    .formatted(shelter.getId()));
        } else {
            shelter.setId(null);
            shelter.setLocationSchemeData(shelterLocationSchemeFile.getBytes());
            shelterRepository.save(shelter);
        }
        return findShelterById(shelter.getId());
    }

    /**
     * Запись информации о схеме размещения (проезда) приюта в базу данных
     * Используется метод репозитория {@link JpaRepository#save(Object)}
     *
     * @param shelterId                 содержит номер (id) приюта
     * @param shelterLocationSchemeFile содержит файл .jpeg со схемой проезда к приюту
     * @throws IOException выбрасывается в случае ошибки ввода-вывода данных при чтении-записи файла
     */
    @Override
    public void saveShelterLocationScheme(Long shelterId, MultipartFile shelterLocationSchemeFile) throws IOException {
        ShelterEntity shelter = findShelterById(shelterId);
        shelter.setLocationSchemeData(shelterLocationSchemeFile.getBytes());
        shelterRepository.save(shelter);
    }

    /**
     * Изменение информации о приюте в базе данных
     * Используются методы репозитория {@link JpaRepository#findById(Object)}
     * и {@link JpaRepository#save(Object)}
     *
     * @param shelter                   содержит обновленные данные о приюте
     * @param shelterLocationSchemeFile содержит обновленный файл .jpeg со схемой проезда к приюту
     * @return обновленная информация о приюте
     * @throws IOException выбрасывается в случае ошибки ввода-вывода данных при чтении-записи файла
     */
    @Override
    public ShelterEntity changeShelter(ShelterEntity shelter, MultipartFile shelterLocationSchemeFile) throws IOException {
        if (findShelterById(shelter.getId()) == null) {
            throw new ShelterDoesNotExistException("Приют номер %d в базе данных отсутствует"
                    .formatted(shelter.getId()));
        }
        shelter.setLocationSchemeData(shelterLocationSchemeFile.getBytes());
        shelterRepository.save(shelter);
        return findShelterById(shelter.getId());
    }

    /**
     * Удаление информации о приюте с указанным номером (id) из базы данных
     * Используется метод репозитория {@link JpaRepository#delete(Object)}
     */
    @Override
    public void deleteShelter(Long id) {
        ShelterEntity shelter = findShelterById(id);
        if (shelter == null) {
            throw new ShelterDoesNotExistException("Приют номер %d в базе данных отсутствует".formatted(id));
        }
        shelterRepository.delete(shelter);

    }

}
