package pro.sky.animal_shelter_ji22_team1_app.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.animal_shelter_ji22_team1_app.entity.ShelterEntity;

import java.util.Collection;
import java.util.List;

public interface ShelterRepository extends JpaRepository<ShelterEntity, Long> {

    @NotNull
    List<ShelterEntity> findAll();

}
