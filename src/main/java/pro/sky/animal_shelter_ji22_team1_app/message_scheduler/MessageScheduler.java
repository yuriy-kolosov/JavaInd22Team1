package pro.sky.animal_shelter_ji22_team1_app.message_scheduler;

import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.UserDoesNotExistException;
import pro.sky.animal_shelter_ji22_team1_app.repository.ReportRepository;
import pro.sky.animal_shelter_ji22_team1_app.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Класс, реализующий подготовку и модификацию данных для рассылки сообщений усыновителям
 * в период их испытательного срока
 *
 * @author yuriy_kolosov
 */

@Component
public class MessageScheduler {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public MessageScheduler(ReportRepository reportRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    Map<Long, String> messagesMap = new HashMap<>();
    LocalDateTime localDateTime = LocalDateTime.now();
    LocalDate localDate = localDateTime.toLocalDate();

    public Map<Long, String> messageCollector() {
        try {
            reportRepository.findAll().stream()
                    .filter((r) -> r.getReportDate().toLocalDate().isEqual(localDate))
                    .filter((c) -> c.getComment() != null)
                    .filter((i) -> !i.isSent())
                    .forEach((s) -> userRepository.findAll().stream()
                            .filter((u) -> u.getId().equals(s.getUser().getId()))
                            .map((f) -> Optional.of(f.getChatId()).orElseThrow())
                            .forEach((d) -> messagesMap.put(d, s.getComment())));
        } catch (UserDoesNotExistException e) {
            throw new UserDoesNotExistException("Пользователь не найден");
        }
        return messagesMap;
    }

    public void messageComplete() {
        try {
            reportRepository.findAll().stream()
                    .filter((r) -> r.getReportDate().toLocalDate().isEqual(localDate))
                    .filter((c) -> c.getComment() != null)
                    .filter((i) -> !i.isSent())
                    .forEach((s) -> {
                        s.setSent(true);
                        reportRepository.save(s);
                        userRepository.findAll().stream()
                                .filter((u) -> u.getId().equals(s.getUser().getId()))
                                .map((f) -> Optional.of(f.getChatId()).orElseThrow())
                                .forEach((d) -> messagesMap.put(d, null));
                    });
        } catch (UserDoesNotExistException e) {
            throw new UserDoesNotExistException("Пользователь не найден");
        }
    }

}
