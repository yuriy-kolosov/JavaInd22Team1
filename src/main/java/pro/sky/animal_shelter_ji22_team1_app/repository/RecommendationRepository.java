package pro.sky.animal_shelter_ji22_team1_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.animal_shelter_ji22_team1_app.entity.RecommendationEntity;

public interface RecommendationRepository extends JpaRepository <RecommendationEntity, Long> {

    @Query(value = "SELECT r.title FROM recommendations r", nativeQuery = true)
    String getTitleById(Long id);

}
