package pro.sky.animal_shelter_ji22_team1_app.service;

import pro.sky.animal_shelter_ji22_team1_app.entity.VolunteerEntity;

import java.util.Collection;

public interface VolunteerService {
    Collection<VolunteerEntity> findAll();

    VolunteerEntity findById(Long id);

    void save(VolunteerEntity volunteer);

    VolunteerEntity change(VolunteerEntity volunteer);

    void delete(Long id);

    VolunteerEntity findByChatId(Long chatId);
}
