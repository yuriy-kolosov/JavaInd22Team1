package pro.sky.animal_shelter_ji22_team1_app.report_safer;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;
import pro.sky.animal_shelter_ji22_team1_app.entity.ReportEntity;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;
import pro.sky.animal_shelter_ji22_team1_app.repository.ReportRepository;
import pro.sky.animal_shelter_ji22_team1_app.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportSafer {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public ReportSafer(ReportRepository reportRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    public void saveReport(Update update) {
        Long chatId = update.message().chat().id();
        UserEntity user = userRepository.findByChatId(chatId);

        List<ReportEntity> reports = reportRepository.findByUserId(user.getId());
        List<ReportEntity> currentDayReports = reports.stream()
                .filter(r -> r.getReportDate().getDayOfYear() == LocalDateTime.now().getDayOfYear())
                .toList();

        if (currentDayReports.isEmpty()) {
            ReportEntity report = new ReportEntity();
            report.setUser(user);
            report.setReportDate(LocalDateTime.now());
            reportRepository.save(report);
        }
    }

    public void SafePhoto(Update update,  byte[] bytes) {
        Long chatId = update.message().chat().id();
        UserEntity user = userRepository.findByChatId(chatId);

        List<ReportEntity> reports = reportRepository.findByUserId(user.getId());
        ReportEntity report = reports.stream()
                .filter(r -> r.getReportDate().getDayOfYear() == LocalDateTime.now().getDayOfYear())
                .findFirst()
                .get();

        report.setAnimalPhoto(bytes);
        reportRepository.save(report);
    }

    public void saveDiet(Update update) {
        Long chatId = update.message().chat().id();
        UserEntity user = userRepository.findByChatId(chatId);

        List<ReportEntity> reports = reportRepository.findByUserId(user.getId());
        ReportEntity report = reports.stream()
                .filter(r -> r.getReportDate().getDayOfYear() == LocalDateTime.now().getDayOfYear())
                .findFirst()
                .get();

        report.setDiet(update.message().text().substring(5));
        reportRepository.save(report);
    }

    public void saveGeneral(Update update) {
        Long chatId = update.message().chat().id();
        UserEntity user = userRepository.findByChatId(chatId);

        List<ReportEntity> reports = reportRepository.findByUserId(user.getId());
        ReportEntity report = reports.stream()
                .filter(r -> r.getReportDate().getDayOfYear() == LocalDateTime.now().getDayOfYear())
                .findFirst()
                .get();

        report.setGeneral(update.message().text().substring(8));
        reportRepository.save(report);
    }

    public void saveBehavior(Update update) {
        Long chatId = update.message().chat().id();
        UserEntity user = userRepository.findByChatId(chatId);

        List<ReportEntity> reports = reportRepository.findByUserId(user.getId());
        ReportEntity report = reports.stream()
                .filter(r -> r.getReportDate().getDayOfYear() == LocalDateTime.now().getDayOfYear())
                .findFirst()
                .get();

        report.setBehavior(update.message().text().substring(9));
        reportRepository.save(report);
    }
}


