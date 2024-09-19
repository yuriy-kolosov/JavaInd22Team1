package pro.sky.animal_shelter_ji22_team1_app.command.shelter_info_menu;

import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.entity.ShelterEntity;
import pro.sky.animal_shelter_ji22_team1_app.service.ShelterService;

import java.util.stream.Collectors;

import static pro.sky.animal_shelter_ji22_team1_app.entity.AnimalType.CAT;
import static pro.sky.animal_shelter_ji22_team1_app.entity.AnimalType.DOG;

@Component
public class ShelterInfoMenu {
    private final ShelterService shelterService;

    public ShelterInfoMenu(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    public String shelterInfo() {
        ShelterEntity shelter = shelterService.findFirst();

        return """
                Вы можете ознакомиться с информацией о приютах для собак и кошек:
                /shelter_rules - общие правила приюта;
                /address_dogs - адрес приюта для собак;
                /address_cats - адрес приюта для кошек;
                /location_dogs - схема проезда к приюту для собак;
                /location_cats - схема проезда к приюту для кошек;
                /schedule_dogs - расписание работы приюта для собак;
                /schedule_cats - расписание работы приюта для кошек;
                /shelter_dogs_contacts - контактные данные охраны и правила оформления пропуска;
                /shelter_cats_contacts - контактные данные охраны и правила оформления пропуска;
                /health_and_safety - общие правила техники безопасности на территории приютов.
                Кроме этого, Вы можете:
                /client_contacts - сообщить Ваши контактные данные;
                /volunteer - получить возможность связаться с волонтером
                """;
    }

    public String shelterRules() {
        return shelterService.findFirst().getRules();
    }

    public String addressDogs() {
        ShelterEntity shelter = shelterService.findAllShelters().stream()
                .filter((t) -> t.getType() == DOG)
                .findAny()
                .orElseThrow();
        return shelter.getAddress();
    }

    public String addressCats() {
        ShelterEntity shelter = shelterService.findAllShelters().stream()
                .filter((t) -> t.getType() == CAT)
                .findAny()
                .orElseThrow();
        return shelter.getAddress();
    }

    public byte[] locationDogs() {
        ShelterEntity shelter = shelterService.findAllShelters().stream()
                .filter((t) -> t.getType() == DOG)
                .findAny()
                .orElseThrow();
        return shelter.getLocationSchemeData();
    }

    public byte[] locationCats() {
        ShelterEntity shelter = shelterService.findAllShelters().stream()
                .filter((t) -> t.getType() == CAT)
                .findAny()
                .orElseThrow();
        return shelter.getLocationSchemeData();
    }

    public String schedulerDogs() {
        ShelterEntity shelter = shelterService.findAllShelters().stream()
                .filter((t) -> t.getType() == DOG)
                .findAny()
                .orElseThrow();
        return shelter.getSchedule();
    }

    public String schedulerCats() {
        ShelterEntity shelter = shelterService.findAllShelters().stream()
                .filter((t) -> t.getType() == CAT)
                .findAny()
                .orElseThrow();
        return shelter.getSchedule();
    }

    public String shelterDogsContacts() {
        ShelterEntity shelter = shelterService.findAllShelters().stream()
                .filter((t) -> t.getType() == DOG)
                .findAny()
                .orElseThrow();
        return shelter.getContacts();
    }

    public String shelterCatsContacts() {
        ShelterEntity shelter = shelterService.findAllShelters().stream()
                .filter((t) -> t.getType() == CAT)
                .findAny()
                .orElseThrow();
        return shelter.getContacts();
    }

    public String healthAndSafety() {
        return shelterService.findFirst().getSafetyRecommendations();
    }

    public String clientContacts() {
        return """
                Давайте сохраним Ваши данные для будущего сотрудничества:
                /firstname - ввести имя;
                /surname - ввести отчество;
                /lastname - ввести фамилию;
                /phone - ввести номер контактного телефона
                """;
    }
}
