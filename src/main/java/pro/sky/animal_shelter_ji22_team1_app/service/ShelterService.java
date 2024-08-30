package pro.sky.animal_shelter_ji22_team1_app.service;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.animal_shelter_ji22_team1_app.entity.ShelterEntity;

import java.io.IOException;
import java.util.List;

public interface ShelterService {

    List<ShelterEntity> findAllShelters();

    ShelterEntity findShelterById(Long shelterId);

    ShelterEntity saveShelter(ShelterEntity shelter);

    ShelterEntity changeShelter(ShelterEntity shelter);

    void saveShelterLocationScheme(Long shelterId, MultipartFile shelterLocationSchemeFile) throws IOException;

    void deleteShelter(Long shelterId);

}
