package pro.sky.animal_shelter_ji22_team1_app.command.entry_menu;

import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.repository.AnimalRepository;
import pro.sky.animal_shelter_ji22_team1_app.repository.RecommendationRepository;

import java.util.Arrays;

import static pro.sky.animal_shelter_ji22_team1_app.entity.AnimalType.*;
import static pro.sky.animal_shelter_ji22_team1_app.entity.TitleType.*;

@Component
public class EntryMenu {

    private final AnimalRepository animalRepository;
    private final RecommendationRepository recommendationRepository;

    public EntryMenu(AnimalRepository animalRepository, RecommendationRepository recommendationRepository) {
        this.animalRepository = animalRepository;
        this.recommendationRepository = recommendationRepository;
    }

    public String entry() {
        return "Приветствуем Ваше желание усыновить животное из приюта!" +
                " Для получения необходимой информации, пожалуйста, сообщите," +
                " какого питомца вы хотите выбрать:" +
                "\n/dog - собака;" +
                "\n/cat - кошка";
    }

    public String dog() {
        return "Чтобы усыновить собаку из приюта," +
                " Вам может понадобиться:" +
                "\n/dogs                - получить список собак для усыновления;" +
                "\n/documents           - получить список документов, необходимых для усыновления животного;" +
                "\n/transportation      - получить список рекомендаций по транспортировке животного;" +
                "\n/puppy_house         - получить список рекомендаций по обустройства дома для щенка;" +
                "\n/pet_house           - получить список рекомендаций по обустройства дома для взрослого животного;" +
                "\n/invalid_pet_house   - получить список рекомендаций по обустройства дома для животного с ограниченными возможностями;" +
                "\n/dog_handler         - получить советы кинолога по первичному обращению с собакой;" +
                "\n/dog_handler_list    - получить список проверенных кинологов;" +
                "\n/waiver_list         - узнать список возможных причин отказа в усыновлении животного" +
                "\n/dog_rules           - получить Правила знакомства с собакой;" +
                "\n/client_contacts     - сообщить свои контактные данные";
    }

    public String cat() {
        return "Приветствуем Ваше желание усыновить животное из приюта" +
                " и информируем о том, что для этого Вам может понадобиться:" +
                "\n/cats                - получить список кошек для усыновления;" +
                "\n/documents           - получить список документов, необходимых для усыновления животного;" +
                "\n/transportation      - получить список рекомендаций по транспортировке животного;" +
                "\n/pet_house           - получить список рекомендаций по обустройства дома для взрослого животного;" +
                "\n/invalid_pet_house   - получить список рекомендаций по обустройства дома для животного с ограниченными возможностями;" +
                "\n/waiver_list         - узнать список возможных причин отказа в усыновлении животного" +
                "\n/cat_rules           - получить Правила знакомства с кошкой;" +
                "\n/client_contacts     - сообщить свои контактные данные";
    }

    public String dogs() {
        return Arrays.toString(animalRepository.findAll().stream()
                .filter((t) -> t.getType().equals(DOG))
                .map((a) -> "Имя животного: " + a.getName().toString() +
                        "; возраст: " + a.getAge().toString() +
                        "; порода: " + a.getBreed().toString())
                .toArray());
    }

    public String cats() {
        return Arrays.toString(animalRepository.findAll().stream()
                .filter((a) -> a.getType().equals(CAT))
                .map((a) -> "Имя животного: " + a.getName() +
                        "; возраст: " + a.getAge().toString() +
                        "; порода: " + a.getBreed())
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
