package pro.sky.animal_shelter_ji22_team1_app.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import pro.sky.animal_shelter_ji22_team1_app.entity.Animal;

public class ConstantsForTests {
    public static final Animal BOBIK = new Animal(
            1L,
            "Bobik",
            1,
            "Dvorteryer",
            LocalDateTime.parse("2024-06-08 11:30:00",
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            "Верный пёс"
    );
    public static final Animal MURKA = new Animal(
            2L,
            "Murka",
            2,
            "Siamskaya",
            LocalDateTime.parse("2024-06-08 12:30:00",
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            "Та ещё вредина");
    public static final Animal SHARIK = new Animal(
            3L,
            "Sharik",
            0,
            "Pudel",
            LocalDateTime.parse("2024-06-08 10:30:00",
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            "Цирковой");


    public static final List<Animal> ANIMALS = List.of(BOBIK, MURKA, SHARIK);


}
