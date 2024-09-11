package pro.sky.animal_shelter_ji22_team1_app.command.entry_menu;

import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.repository.AnimalRepository;
import pro.sky.animal_shelter_ji22_team1_app.repository.RecommendationRepository;

import java.util.Arrays;

@Component
public class EntryMenu {
    private final AnimalRepository animalRepository;

    private final RecommendationRepository recommendationRepository;

    public EntryMenu(AnimalRepository animalRepository, RecommendationRepository recommendationRepository) {
        this.animalRepository = animalRepository;
        this.recommendationRepository = recommendationRepository;
    }

    public String entry() {
        return "Приветствуем Ваше желание усыновить животное из приюта" +
               " и информируем о том, что для этого Вам может понадобиться:" +
               "\n/pets            - получить список домашних животных для усыновления;" +
               "\n/rules           - получить Правила знакомства с животным;" +
               "\n/documents       - получить список документов, необходимых для усыновления животного;" +
               "\n/transportation  - получить список рекомендаций по транспортировке животного;" +
               "\n/puppyhouse      - получить список рекомендаций по обустройства дома для щенка;" +
               "\n/pethouse        - получить список рекомендаций по обустройства дома для взрослого животного;" +
               "\n/invalidpethouse - получить список рекомендаций по обустройства дома для животного с ограниченными возможностями;" +
               "\n/doghandler      - получить советы кинолога по первичному обращению с собакой;" +
               "\n/doghandlerlist  - получить список проверенных кинологов;" +
               "\n/waiverlist      - узнать список возможных причин отказа в усыновлении животного";
    }

    public String pets() {
        return Arrays.toString(animalRepository.findAll().stream()
                .map((a) -> "Имя животного: " + a.getName().toString() +
                            "; возраст: " + a.getAge().toString() +
                            "; порода: " + a.getBreed().toString())
                .toArray());
    }

    public String rules() {
        return recommendationRepository.getTitleById(1L);

    }
}
