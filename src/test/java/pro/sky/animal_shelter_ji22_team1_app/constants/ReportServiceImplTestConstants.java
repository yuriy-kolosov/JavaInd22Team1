package pro.sky.animal_shelter_ji22_team1_app.constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import pro.sky.animal_shelter_ji22_team1_app.entity.ReportEntity;

public class ReportServiceImplTestConstants {

    public static final ReportEntity REPORT1 = new ReportEntity(
            1L,
            1L,
            "Всё подряд",
            "Здоровее некуда",
            "Чувствует себя как дома",
            "Стал больше играться",
            LocalDateTime
                    .of(LocalDate.of(2024, 01, 01)
                            , LocalTime.of(12, 00, 00, 0)),
            "all good"
    );
    public static final ReportEntity REPORT2 = new ReportEntity(
            2L,
            2L,
            "Всё кроме конфет",
            "Вялость",
            "Обживается",
            "Стала царапать мебель",
            LocalDateTime
                    .of(LocalDate.of(2024, 02, 02)
                            , LocalTime.of(13, 00, 00, 0)),
            "норм"
    );

    public static final List<ReportEntity> REPORTS = List.of(REPORT1, REPORT2);
}
