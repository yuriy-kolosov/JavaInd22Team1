package pro.sky.animal_shelter_ji22_team1_app.service;

import java.util.Collection;
import org.springframework.stereotype.Service;
import pro.sky.animal_shelter_ji22_team1_app.entity.AnimalEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.AnimalDoesNotExistException;
import pro.sky.animal_shelter_ji22_team1_app.repository.AnimalRepository;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    /**
     * Поиск всех животных приюта
     *
     * @return animals
     */
    @Override public Collection<AnimalEntity> findAll() {
        return animalRepository.findAll();
    }

    /**
     * Поиск животного приюта по идентификатору
     *
     * @param id
     * @return найденное животное
     * @throws AnimalDoesNotExistException если животное с указанным идентификатором не было найдено
     */
    @Override public AnimalEntity findById(Long id) {
        return animalRepository.findById(id)
                               .orElseThrow(() -> new AnimalDoesNotExistException("Животного с id = %d не существует".formatted(id)));
    }

    /**
     * Создание нового животного приюта
     *
     * @param animal
     */
    @Override public AnimalEntity save(AnimalEntity animal) {
        return animalRepository.save(animal);
    }

    /**
     * Изменение данных животного приюта
     *
     * @param animal
     * @throws AnimalDoesNotExistException если клиента с таким идентификатором существует
     */
    @Override public AnimalEntity change(AnimalEntity animal) {
        if (findById(animal.getId()) == null) {
            throw new AnimalDoesNotExistException("Животного с id = %d не существует".formatted(animal.getId()));
        }
        save(animal);
        return findById(animal.getId());
    }

    /**
     * удаление животного приюта по идентификатору
     *
     * @param id
     * @throws AnimalDoesNotExistException если животное с таким идентификатором не существует
     */
    @Override public AnimalEntity delete(Long id) {

        return animalRepository.findById(id)
                                         .map(a -> {
                                             animalRepository.delete(a);
                                             return a;
                                         })
                                         .orElseThrow(() -> {
                                             throw new AnimalDoesNotExistException("Животное с id = %d не существует".formatted(id));
                                         });
    }
}
