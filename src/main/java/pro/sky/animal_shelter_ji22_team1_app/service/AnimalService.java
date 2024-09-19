package pro.sky.animal_shelter_ji22_team1_app.service;

import java.util.Collection;
import pro.sky.animal_shelter_ji22_team1_app.entity.AnimalEntity;

public interface AnimalService {
    Collection<AnimalEntity> findAll();

    AnimalEntity findById(Long animalId);

    AnimalEntity save(AnimalEntity animal);

    AnimalEntity change(AnimalEntity animal);

    AnimalEntity delete(Long animalId);
}
