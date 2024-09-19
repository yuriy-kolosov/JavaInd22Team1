package pro.sky.animal_shelter_ji22_team1_app.repository;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.animal_shelter_ji22_team1_app.entity.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

    @NotNull
    List<ReportEntity> findAll();

    List<ReportEntity> findByUserId(Long id);
}
