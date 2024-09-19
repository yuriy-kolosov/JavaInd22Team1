package pro.sky.animal_shelter_ji22_team1_app.constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import pro.sky.animal_shelter_ji22_team1_app.entity.ReportEntity;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;

public class ReportServiceImplTestConstants {
    static UserEntity user1 = new UserEntity();
    static UserEntity user2 = new UserEntity();
    public static final ReportEntity REPORT1 = new ReportEntity(
            1L,
            "Всё подряд",
            "image/jpg",
            "Здоровее некуда",
            "Чувствует себя как дома",
            LocalDateTime
                    .of(LocalDate.of(2024, 01, 01)
                            , LocalTime.of(12, 00, 00, 0)),
            true,
            user1
    );
    public static final ReportEntity REPORT2 = new ReportEntity(
            2L,
            "image/jpg",
            "Вялость",
            "Обживается",
            "Стала царапать мебель",
            LocalDateTime
                    .of(LocalDate.of(2024, 02, 02)
                            , LocalTime.of(13, 00, 00, 0)),
            true,
            user2
    );

    public static final List<ReportEntity> REPORTS = List.of(REPORT1, REPORT2);
}
