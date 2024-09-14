package pro.sky.animal_shelter_ji22_team1_app.command.entry_menu;

import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.repository.AnimalRepository;
import pro.sky.animal_shelter_ji22_team1_app.repository.RecommendationRepository;

import java.util.Arrays;

import static pro.sky.animal_shelter_ji22_team1_app.entity.AnimalType.*;
import static pro.sky.animal_shelter_ji22_team1_app.entity.TitleType.*;

/**
 * Класс, реализующий меню диалога с клиентом на этапе выбора питомца (Point 2)
 *
 * @author yuriy_kolosov
 */
@Component
public class EntryMenu {

    private final AnimalRepository animalRepository;
    private final RecommendationRepository recommendationRepository;

    public EntryMenu(AnimalRepository animalRepository, RecommendationRepository recommendationRepository) {
        this.animalRepository = animalRepository;
        this.recommendationRepository = recommendationRepository;
    }

    public String entry() {
        return """
                Приветствуем Ваше желание усыновить животное из приюта!
                Для получения необходимой информации, пожалуйста, сообщите,
                какого питомца вы хотите выбрать:
                /dog - собака;
                /cat - кошка
                """;
    }

    public String dog() {
        return """
                Чтобы усыновить собаку из приюта,
                Вам может понадобиться:
                /dogs - получить список собак для усыновления;
                /documents - получить список документов, необходимых для усыновления животного;
                /transportation - получить список рекомендаций по транспортировке животного;
                /puppy_house - получить список рекомендаций по обустройства дома для щенка;
                /pet_house - получить список рекомендаций по обустройства дома для взрослого животного;
                /invalid_pet_house - получить список рекомендаций по обустройства дома для животного с ограниченными возможностями;
                /dog_handler - получить советы кинолога по первичному обращению с собакой;
                /dog_handler_list - получить список проверенных кинологов;
                /waiver_list - узнать список возможных причин отказа в усыновлении животного;
                /dog_rules - получить Правила знакомства с собакой;
                /client_contacts - сообщить свои контактные данные
                """;
    }

    public String cat() {
        return """
                Приветствуем Ваше желание усыновить животное из приюта
                и информируем о том, что для этого Вам может понадобиться:
                /cats - получить список кошек для усыновления;
                /documents - получить список документов, необходимых для усыновления животного;
                /transportation - получить список рекомендаций по транспортировке животного;
                /pet_house - получить список рекомендаций по обустройства дома для взрослого животного;
                /invalid_pet_house - получить список рекомендаций по обустройства дома для животного с ограниченными возможностями;
                /waiver_list - узнать список возможных причин отказа в усыновлении животного;
                /cat_rules - получить Правила знакомства с кошкой;
                /client_contacts - сообщить свои контактные данные
                """;
    }

    public String dogs() {
        return Arrays.toString(animalRepository.findAll().stream()
                .filter((t) -> t.getType().equals(DOG))
                .map((a) -> "Имя животного: " + a.getName().toString() +
                        ";\n возраст: " + a.getAge().toString() +
                        ";\n порода: " + a.getBreed().toString() +
                        "\n")
                .toArray());
    }

    public String cats() {
        return Arrays.toString(animalRepository.findAll().stream()
                .filter((a) -> a.getType().equals(CAT))
                .map((a) -> "Имя животного: " + a.getName() +
                        ";\n возраст: " + a.getAge().toString() +
                        ";\n порода: " + a.getBreed() +
                        "\n")
                .toArray());
    }

    public String documents() {
        return Arrays.toString(recommendationRepository.findAll().stream()
                .filter((t) -> t.getTitle().equals(DOCUMENTS))
                .map((d) -> "<> " + d.getDescription())
                .toArray());
    }

    public String transportation() {
        return Arrays.toString(recommendationRepository.findAll().stream()
                .filter((t) -> t.getTitle().equals(TRANSPORTATION))
                .map((d) -> "<> " + d.getDescription())
                .toArray());
    }

    public String petHouse() {
        return Arrays.toString(recommendationRepository.findAll().stream()
                .filter((t) -> t.getTitle().equals(PET_HOUSE))
                .map((d) -> "<> " + d.getDescription())
                .toArray());
    }

    public String invalidPetHouse() {
        return Arrays.toString(recommendationRepository.findAll().stream()
                .filter((t) -> t.getTitle().equals(INVALID_PET_HOUSE))
                .map((d) -> "<> " + d.getDescription())
                .toArray());
    }

    public String waiverList() {
        return Arrays.toString(recommendationRepository.findAll().stream()
                .filter((t) -> t.getTitle().equals(WAIVER_LIST))
                .map((d) -> "<> " + d.getDescription())
                .toArray());
    }

    public String dogRules() {
        return Arrays.toString(recommendationRepository.findAll().stream()
                .filter((t) -> t.getTitle().equals(DOG_RULES))
                .map((r) -> "<> " + r.getDescription())
                .toArray());
    }

    public String puppyHouse() {
        return Arrays.toString(recommendationRepository.findAll().stream()
                .filter((t) -> t.getTitle().equals(PUPPY_HOUSE))
                .map((d) -> "<> " + d.getDescription())
                .toArray());
    }

    public String dogHandler() {
        return Arrays.toString(recommendationRepository.findAll().stream()
                .filter((t) -> t.getTitle().equals(DOG_HANDLER))
                .map((d) -> "<> " + d.getDescription())
                .toArray());
    }

    public String dogHandlerList() {
        return Arrays.toString(recommendationRepository.findAll().stream()
                .filter((t) -> t.getTitle().equals(DOG_HANDLER_LIST))
                .map((d) -> "<> " + d.getDescription())
                .toArray());
    }


    public String catRules() {
        return Arrays.toString(recommendationRepository.findAll().stream()
                .filter((t) -> t.getTitle().equals(CAT_RULES))
                .map((r) -> "<> " + r.getDescription())
                .toArray());
    }

}
