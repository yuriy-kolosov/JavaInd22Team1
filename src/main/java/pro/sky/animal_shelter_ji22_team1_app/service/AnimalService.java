package pro.sky.animal_shelter_ji22_team1_app.service;

import java.util.Collection;
import pro.sky.animal_shelter_ji22_team1_app.entity.Animal;

public interface AnimalService {
    Collection<Animal> findAll();

    Animal findById(Long animalId);

    Animal save(Animal animal);

    Animal change(Animal animal);

    Animal delete(Long animalId);
}
