package pro.sky.animal_shelter_ji22_team1_app.constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import pro.sky.animal_shelter_ji22_team1_app.entity.AnimalEntity;

public class AnimalServiceImplTestConstants {
    public static final AnimalEntity BOBIK = new AnimalEntity(
            1L,
            "Bobik",
            1,
            "Dvorteryer",
            LocalDateTime.parse("2024-06-08 11:30:00",
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            "Верный пёс"
    );
    public static final AnimalEntity MURKA = new AnimalEntity(
            2L,
            "Murka",
            2,
            "Siamskaya",
            LocalDateTime.parse("2024-06-08 12:30:00",
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            "Та ещё вредина");
    public static final AnimalEntity SHARIK = new AnimalEntity(
            3L,
            "Sharik",
            0,
            "Pudel",
            LocalDateTime.parse("2024-06-08 10:30:00",
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            "Цирковой");


    public static final List<AnimalEntity> ANIMALS = List.of(BOBIK, MURKA, SHARIK);


}
