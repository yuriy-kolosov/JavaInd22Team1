package pro.sky.animal_shelter_ji22_team1_app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.animal_shelter_ji22_team1_app.entity.AnimalEntity;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {
    List<AnimalEntity> findByName(String name);
}
