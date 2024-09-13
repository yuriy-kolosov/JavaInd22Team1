package pro.sky.animal_shelter_ji22_team1_app.service;

import java.util.Collection;
import pro.sky.animal_shelter_ji22_team1_app.entity.ReportEntity;

public interface ReportService {

    Collection<ReportEntity> findAllReports();

    ReportEntity findReportById(Long reportId);

    void saveReport(ReportEntity report);

    ReportEntity changeReport(ReportEntity report);

    void deleteReport(Long reportId);

}
