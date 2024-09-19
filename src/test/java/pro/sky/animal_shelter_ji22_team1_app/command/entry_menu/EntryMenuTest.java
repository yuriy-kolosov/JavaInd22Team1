package pro.sky.animal_shelter_ji22_team1_app.command.entry_menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pro.sky.animal_shelter_ji22_team1_app.entity.AnimalEntity;
import pro.sky.animal_shelter_ji22_team1_app.entity.RecommendationEntity;
import pro.sky.animal_shelter_ji22_team1_app.entity.TitleType;
import pro.sky.animal_shelter_ji22_team1_app.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static pro.sky.animal_shelter_ji22_team1_app.constants.EntryMenuTestConstants.*;
import static pro.sky.animal_shelter_ji22_team1_app.constants.EntryMenuTestConstants.DOG_HANDLER_LIST;
import static pro.sky.animal_shelter_ji22_team1_app.entity.AnimalType.CAT;
import static pro.sky.animal_shelter_ji22_team1_app.entity.AnimalType.DOG;
import static pro.sky.animal_shelter_ji22_team1_app.entity.TitleType.*;

public class EntryMenuTest {

    final AnimalRepository animalRepository = Mockito.mock(AnimalRepository.class);
    final RecommendationRepository recommendationRepository = Mockito.mock(RecommendationRepository.class);

    public EntryMenuTest() {
    }

    @Test
    public void entryTest() {
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);
        Assertions.assertEquals(ENTRY_WELCOME, entryMenu.entry());
    }

    @Test
    public void dogTest() {
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);
        Assertions.assertEquals(DOG_WELCOME, entryMenu.dog());
    }

    @Test
    public void catTest() {
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);
        Assertions.assertEquals(CAT_WELCOME, entryMenu.cat());
    }

    @Test
    public void dogsTest() {
//        Подготовка
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);
        LocalDateTime localDateTime = LocalDateTime
                .of(LocalDate.of(2023, 01, 01)
                        , LocalTime.of(12, 01, 01, 0));
        AnimalEntity animalEntity = new AnimalEntity(1L
                , "Полкан"
                , 2
                , DOG
                , "дворняжка"
                , localDateTime
                , "No comment");
        List<AnimalEntity> animalEntities = new ArrayList<>();
        animalEntities.add(animalEntity);

        Mockito.when(animalRepository.findAll()).thenReturn(animalEntities);
//          Выполнение
        Assertions.assertEquals(DOGS_LIST, entryMenu.dogs());
    }

    @Test
    public void catsTest() {
//        Подготовка
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);
        LocalDateTime localDateTime = LocalDateTime
                .of(LocalDate.of(2023, 01, 01)
                        , LocalTime.of(12, 01, 01, 0));
        AnimalEntity animalEntity = new AnimalEntity(1L
                , "Мурка"
                , 1
                , CAT
                , "цветная"
                , localDateTime
                , "No comment");
        List<AnimalEntity> animalEntities = new ArrayList<>();
        animalEntities.add(animalEntity);

        Mockito.when(animalRepository.findAll()).thenReturn(animalEntities);
//          Выполнение
        Assertions.assertEquals(CATS_LIST, entryMenu.cats());
    }

    @Test
    public void documentsTest() {
//        Подготовка
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);

        RecommendationEntity recommendation = new RecommendationEntity(1L
                , DOG
                , DOCUMENTS
                , "Список документов для усыновления");

        List<RecommendationEntity> recommendationEntities = new ArrayList<>();
        recommendationEntities.add(recommendation);

        Mockito.when(recommendationRepository.findAll()).thenReturn(recommendationEntities);
//          Выполнение
        Assertions.assertEquals(DOCUMENTS_LIST, entryMenu.documents());
    }

    @Test
    public void transportationTest() {
//        Подготовка
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);

        RecommendationEntity recommendation = new RecommendationEntity(1L
                , DOG
                , TRANSPORTATION
                , "Список рекомендаций по транспортировке животного");

        List<RecommendationEntity> recommendationEntities = new ArrayList<>();
        recommendationEntities.add(recommendation);

        Mockito.when(recommendationRepository.findAll()).thenReturn(recommendationEntities);
//          Выполнение
        Assertions.assertEquals(TRANSPORTATION_LIST, entryMenu.transportation());
    }

    @Test
    public void petHouseTest() {
//        Подготовка
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);

        RecommendationEntity recommendation = new RecommendationEntity(1L
                , DOG
                , PET_HOUSE
                , "Список рекомендаций по обустройству дома для взрослого животного");

        List<RecommendationEntity> recommendationEntities = new ArrayList<>();
        recommendationEntities.add(recommendation);

        Mockito.when(recommendationRepository.findAll()).thenReturn(recommendationEntities);
//          Выполнение
        Assertions.assertEquals(PET_HOUSE_LIST, entryMenu.petHouse());
    }

    @Test
    public void invalidPetHouseTest() {
//        Подготовка
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);

        RecommendationEntity recommendation = new RecommendationEntity(1L
                , DOG
                , INVALID_PET_HOUSE
                , "Список рекомендаций по обустройству дома для животного с ограниченными возможностями");

        List<RecommendationEntity> recommendationEntities = new ArrayList<>();
        recommendationEntities.add(recommendation);

        Mockito.when(recommendationRepository.findAll()).thenReturn(recommendationEntities);
//          Выполнение
        Assertions.assertEquals(INVALID_PET_HOUSE_LIST, entryMenu.invalidPetHouse());
    }

    @Test
    public void waiverListTest() {
//        Подготовка
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);

        RecommendationEntity recommendation = new RecommendationEntity(1L
                , DOG
                , WAIVER_LIST
                , "Список возможных причин отказа в усыновлении животного");

        List<RecommendationEntity> recommendationEntities = new ArrayList<>();
        recommendationEntities.add(recommendation);

        Mockito.when(recommendationRepository.findAll()).thenReturn(recommendationEntities);
//          Выполнение
        Assertions.assertEquals(WAIVER_LIST_LIST, entryMenu.waiverList());
    }

    @Test
    public void dogRulesTest() {
//        Подготовка
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);

        RecommendationEntity recommendation = new RecommendationEntity(1L
                , DOG
                , DOG_RULES
                , "Правила знакомства с животным (собака)");

        List<RecommendationEntity> recommendationEntities = new ArrayList<>();
        recommendationEntities.add(recommendation);

        Mockito.when(recommendationRepository.findAll()).thenReturn(recommendationEntities);
//          Выполнение
        Assertions.assertEquals(DOG_RULES_LIST, entryMenu.dogRules());
    }

    @Test
    public void puppyHouseTest() {
//        Подготовка
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);

        RecommendationEntity recommendation = new RecommendationEntity(1L
                , DOG
                , PUPPY_HOUSE
                , "Список рекомендаций по обустройству дома для щенка");

        List<RecommendationEntity> recommendationEntities = new ArrayList<>();
        recommendationEntities.add(recommendation);

        Mockito.when(recommendationRepository.findAll()).thenReturn(recommendationEntities);
//          Выполнение
        Assertions.assertEquals(PUPPY_HOUSE_LIST, entryMenu.puppyHouse());
    }

    @Test
    public void dogHandlerTest() {
//        Подготовка
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);

        RecommendationEntity recommendation = new RecommendationEntity(1L
                , DOG
                , DOG_HANDLER
                , "Советы кинолога по первичному обращению с собакой");

        List<RecommendationEntity> recommendationEntities = new ArrayList<>();
        recommendationEntities.add(recommendation);

        Mockito.when(recommendationRepository.findAll()).thenReturn(recommendationEntities);
//          Выполнение
        Assertions.assertEquals(DOG_HANDLER_LIST, entryMenu.dogHandler());
    }

    @Test
    public void dogHandlerListTest() {
//        Подготовка
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);

        RecommendationEntity recommendation = new RecommendationEntity(1L
                , DOG
                , TitleType.DOG_HANDLER_LIST
                , "Список проверенных кинологов");

        List<RecommendationEntity> recommendationEntities = new ArrayList<>();
        recommendationEntities.add(recommendation);

        Mockito.when(recommendationRepository.findAll()).thenReturn(recommendationEntities);
//          Выполнение
        Assertions.assertEquals(DOG_HANDLER_LIST_LIST, entryMenu.dogHandlerList());
    }

    @Test
    public void catRulesTest() {
//        Подготовка
        final EntryMenu entryMenu = new EntryMenu(animalRepository, recommendationRepository);

        RecommendationEntity recommendation = new RecommendationEntity(1L
                , CAT
                , CAT_RULES
                , "Правила знакомства с животным(кошка)");

        List<RecommendationEntity> recommendationEntities = new ArrayList<>();
        recommendationEntities.add(recommendation);

        Mockito.when(recommendationRepository.findAll()).thenReturn(recommendationEntities);
//          Выполнение
        Assertions.assertEquals(CAT_RULES_LIST, entryMenu.catRules());
    }

}
