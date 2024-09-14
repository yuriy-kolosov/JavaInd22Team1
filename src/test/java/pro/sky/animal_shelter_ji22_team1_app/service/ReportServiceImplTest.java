package pro.sky.animal_shelter_ji22_team1_app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static pro.sky.animal_shelter_ji22_team1_app.constants.ReportServiceImplTestConstants.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pro.sky.animal_shelter_ji22_team1_app.repository.ReportRepository;

class ReportServiceImplTest {


    private ReportRepository reportRepository = Mockito.mock(ReportRepository.class);

    private ReportServiceImpl reportService = new ReportServiceImpl(reportRepository);


    @Test
    void findAll() {
        when(reportRepository.findAll()).thenReturn(REPORTS);
        assertEquals(REPORTS, reportService.findAll());
    }

    @Test
    void findById() {
        when(reportRepository.findById(1L)).thenReturn(Optional.of(REPORT1));
        assertEquals(reportService.findById(1L), REPORT1);
    }

    @Test
    void change() {
        when(reportRepository.findById(2L)).thenReturn(Optional.of(REPORT2));
        assertEquals(REPORT2, reportService.change(REPORT2));
    }
}