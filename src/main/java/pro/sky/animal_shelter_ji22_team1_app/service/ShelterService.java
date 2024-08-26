package pro.sky.animal_shelter_ji22_team1_app.service;

import pro.sky.animal_shelter_ji22_team1_app.entity.ShelterEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.ShelterDoesNotExistException;

import java.util.List;

public interface ShelterService {

    List<ShelterEntity> findAllShelters();

    ShelterEntity findShelterById(Long shelterId);

    void saveShelter(ShelterEntity shelter);

    ShelterEntity changeShelter(ShelterEntity shelter);

    void deleteShelter(Long shelterId);

}
