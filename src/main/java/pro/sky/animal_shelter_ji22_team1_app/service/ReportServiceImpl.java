package pro.sky.animal_shelter_ji22_team1_app.service;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pro.sky.animal_shelter_ji22_team1_app.entity.ReportEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.ReportDoesNotExistException;
import pro.sky.animal_shelter_ji22_team1_app.repository.ReportRepository;

/**
 * Класс, имплементирующий интерфейс сервиса Report
 *
 * @author Mariia Merkel
 */
@Service
public class ReportServiceImpl implements ReportService {

    public final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Вывод информации об отчётах
     * Используется метод репозитория {@link JpaRepository#findAll()}
     *
     * @return информация со списком отчётов
     */
    @Override
    public List<ReportEntity> findAll() {
        return reportRepository.findAll();
    }

    /**
     * Вывод информации об отчёте по его номеру (id)
     * Используется метод репозитория {@link JpaRepository#findById(Object)}
     */
    @Override
    public ReportEntity findById(Long id) {
        return reportRepository.findById(id)
                               .orElseThrow(() -> new ReportDoesNotExistException("Отчёт с номером %d в базе данных отсутствует"
                                                                                          .formatted(id)));
    }

    /**
     * Запись информации об отчёте в базу данных
     * Используется метод репозитория {@link JpaRepository#save(Object)}
     *
     * @param report содержит информацию об отчёте
     */
    @Override
    public void save(ReportEntity report) {
        report.setId(null);
        reportRepository.save(report);
    }

    /**
     * Изменение информации об отчёте
     * Используются методы репозитория {@link JpaRepository#findById(Object)}
     * и {@link JpaRepository#save(Object)}
     *
     * @param report содержит обновленные данные об отчёте
     * @return обновленная информация об отчёте
     */
    @Override
    public ReportEntity change(ReportEntity report) {
        ReportEntity reportCurrent = reportRepository.findById(report.getId())
                                                     .orElseThrow(() -> new ReportDoesNotExistException("Отчёт с номером %d в базе данных отсутствует"
                                                                                                                .formatted(report.getId())));
        reportRepository.save(report);
        report.setId(reportCurrent.getId());
        return report;
    }

    /**
     * Удаление информации об отчёте с указанным номером (id)
     * Используется метод репозитория {@link JpaRepository#delete(Object)}
     */
    @Override
    public void delete(Long id) {
        ReportEntity report = findById(id);
        if (report == null) {
            throw new ReportDoesNotExistException("Отчёт с номером %d в базе данных отсутствует".formatted(id));
        }
        reportRepository.delete(report);

    }

}
