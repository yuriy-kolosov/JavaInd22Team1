package pro.sky.animal_shelter_ji22_team1_app.command;

import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.entity.ShelterEntity;
import pro.sky.animal_shelter_ji22_team1_app.repository.AnimalRepository;
import pro.sky.animal_shelter_ji22_team1_app.repository.RecommendationRepository;
import pro.sky.animal_shelter_ji22_team1_app.service.ShelterService;

import java.util.Arrays;

@Component
public class Menu {

    private final AnimalRepository animalRepository;
    private final RecommendationRepository recommendationRepository;

    private final ShelterService shelterService;

    public Menu(AnimalRepository animalRepository, RecommendationRepository recommendationRepository, ShelterService shelterService) {
        this.animalRepository = animalRepository;
        this.recommendationRepository = recommendationRepository;
        this.shelterService = shelterService;
    }

    public String start() {
        return "Доброго времени суток, я бот, который помогает животным, оказавшимся в сложной ситуации, " +
               "и людям, которые хотят найти себе нового пушистого друга, обрести то, что они ищут. " +
               "Хотите узнать больше о нас, - введите команду /help";
    }

    public String help() {
        return "Доступны следующие команды:" +
//                                                                          Point 0 commands
               "\n/volunteer       - связаться с волонтером;" +
//                                                                          Point 1 commands
               "\n/shelter_info    - узнать информацию о приюте;" +
//                                                                              ...
//                                                                          Point 2 commands
               "\n/entry           - получить подробную информацию о том, как взять животное из приюта;" +
//                                                                              ...
//                                                                          Point 3 commands
               "\n/dailyreportform - прислать ежедневный отчет о содержании животного";
//                                                                              ...
    }

    public String shelterInfo() {
        ShelterEntity shelter = shelterService.findShelterById(1L);

        return """
                Приют "%s" расположен по адресу: %s") +
                /location - Чтобы ознакомиться со схемой проезда к нему
                /shelter_contacts - Контактные данные охраны и правила оформления пропуска+
                /health_and_safety - Общие правила техники безопасности на территории приюта
                /client_contacts - Оставить контактные данные"+
                /volunteer - связаться с волонтером
                """.formatted(shelter.getName(), shelter.getContacts());
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

    public String dailyReportForm() {
        return "Информация об отчетах";
    }

    public String location(){
        return "информация о проезде к приюту";
    }

    public String shelterContacts(){
        return "контактные данные охраны и схемы проезда";
    }

    public String healthAndSafety(){
        return "информация о технике безопасности";
    }

    public String clientContacts(){
        return "запись контактной информации";
    }
}
