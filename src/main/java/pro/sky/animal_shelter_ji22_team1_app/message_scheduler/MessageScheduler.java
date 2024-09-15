package pro.sky.animal_shelter_ji22_team1_app.message_scheduler;

import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;
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

    public Map<Long, String> messageSender() {
        reportRepository.findAll().stream()
                .filter((r) -> r.getReportDate().toLocalDate().isEqual(localDate))
                .filter((c) -> c.getComment() != null)
                .filter((i) -> !i.isSent())
                .forEach((s) -> {
                    Optional<Long> found = Optional.empty();
                    for (UserEntity u : userRepository.findAll()) {
                        if (u.getId().equals(s.getUser().getId())) {
                            Long chatId = u.getChatId();
                            found = Optional.of(chatId);
                            break;
                        }
                    }
                    messagesMap
                            .put(found.orElseThrow()
                                    , s.getComment());
                });
        return messagesMap;
    }

    public void messageSenderFlag() {
        reportRepository.findAll().stream()
                .filter((r) -> r.getReportDate().toLocalDate().isEqual(localDate))
                .filter((c) -> c.getComment() != null)
                .filter((i) -> !i.isSent())
                .forEach((s) -> {
                    s.setSent(true);
                    reportRepository.save(s);
                });
    }

}
