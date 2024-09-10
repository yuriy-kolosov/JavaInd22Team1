package pro.sky.animal_shelter_ji22_team1_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.animal_shelter_ji22_team1_app.entity.VolunteerEntity;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<VolunteerEntity, Long> {
    List<VolunteerEntity> findByLogin(String login);

    List<VolunteerEntity> findByPhone(String phone);

    VolunteerEntity findByChatId(Long chatId);
}
