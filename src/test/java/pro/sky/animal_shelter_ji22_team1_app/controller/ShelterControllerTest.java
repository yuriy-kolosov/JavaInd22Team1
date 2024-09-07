package pro.sky.animal_shelter_ji22_team1_app.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import pro.sky.animal_shelter_ji22_team1_app.entity.ShelterEntity;

import static pro.sky.animal_shelter_ji22_team1_app.constants.ShelterControllerTestConstants.*;

/**
 * Класс тестирования контроллера ShelterController
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShelterControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ShelterController shelterController;

    @Autowired
    private TestRestTemplate restTemplate;

    public ShelterControllerTest() {
    }

    @Test
    void getAllSheltersTest() throws Exception {
        Assertions.assertThat(shelterController).isNotNull();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/shelters"
                        , String.class))
                .isNotNull();
    }

    @Test
    void getShelterTest() throws Exception {
        Assertions.assertThat(shelterController).isNotNull();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/shelters/"
                        , String.class
                        , SHELTER_ID))
                .isNotNull();
    }

    @Test
    void getShelterLocationSchemeTest() throws Exception {
        Assertions.assertThat(shelterController).isNotNull();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/location/"
                        , String.class
                        , SHELTER_ID))
                .isNotNull();
    }

    @Test
    void createShelterTest() throws Exception {
        ShelterEntity shelter = new ShelterEntity();
        shelter.setId(SHELTER_ID);
        shelter.setName(SHELTER_NAME);
        shelter.setType(SHELTER_TYPE);
        shelter.setContacts(SHELTER_CONTACTS);
        shelter.setMediaType(SHELTER_MEDIA_TYPE);
        shelter.setRules(SHELTER_RULES);
        shelter.setLocationSchemeData(null);
        this.restTemplate.postForObject("http://localhost:" + port + "/shelters/location_without", shelter, ResponseEntity.class);
    }

    @Test
    void updateShelterTest() throws Exception {
        ShelterEntity shelter = new ShelterEntity();
        shelter.setId(SHELTER_ID);
        shelter.setName(SHELTER_NAME);
        shelter.setType(SHELTER_TYPE);
        shelter.setContacts(SHELTER_CONTACTS);
        shelter.setMediaType(SHELTER_MEDIA_TYPE);
        shelter.setRules(SHELTER_RULES);
        shelter.setLocationSchemeData(null);
        this.restTemplate.put("http://localhost:" + port + "/shelters", shelter);
    }

    @Test
    void addShelterLocationSchemeTest() throws Exception {
        ShelterEntity shelter = new ShelterEntity();
        shelter.setId(SHELTER_ID);
        shelter.setName(SHELTER_NAME);
        shelter.setType(SHELTER_TYPE);
        shelter.setContacts(SHELTER_CONTACTS);
        shelter.setMediaType(SHELTER_MEDIA_TYPE);
        shelter.setRules(SHELTER_RULES);
        shelter.setLocationSchemeData(null);
        this.restTemplate.put("http://localhost:" + port + "/shelters/location/"
                , shelter
                , SHELTER_ID);
    }

    @Test
    void deleteShelterTest() throws Exception {
        ShelterEntity shelter = new ShelterEntity();
        shelter.setId(SHELTER_ID);
        shelter.setName(SHELTER_NAME);
        shelter.setType(SHELTER_TYPE);
        shelter.setContacts(SHELTER_CONTACTS);
        shelter.setMediaType(SHELTER_MEDIA_TYPE);
        shelter.setRules(SHELTER_RULES);
        shelter.setLocationSchemeData(null);
        this.restTemplate.delete("http://localhost:" + port + "/shelters"
                , shelter
                , SHELTER_ID);
    }

}
