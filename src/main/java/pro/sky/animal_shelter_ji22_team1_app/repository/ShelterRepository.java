package pro.sky.animal_shelter_ji22_team1_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.animal_shelter_ji22_team1_app.entity.ShelterEntity;

public interface ShelterRepository extends JpaRepository<ShelterEntity, Long> {

}
