package pro.sky.animal_shelter_ji22_team1_app.service;

import java.util.Collection;
import pro.sky.animal_shelter_ji22_team1_app.entity.ReportEntity;

public interface ReportService {

    Collection<ReportEntity> findAll();

    ReportEntity findById(Long reportId);

    void save(ReportEntity report);

    ReportEntity change(ReportEntity report);

    void delete(Long reportId);

}
