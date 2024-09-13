package pro.sky.animal_shelter_ji22_team1_app.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.animal_shelter_ji22_team1_app.entity.ShelterEntity;
import pro.sky.animal_shelter_ji22_team1_app.repository.AnimalRepository;
import pro.sky.animal_shelter_ji22_team1_app.repository.ShelterRepository;
import pro.sky.animal_shelter_ji22_team1_app.repository.UserRepository;
import pro.sky.animal_shelter_ji22_team1_app.repository.VolunteerRepository;
import pro.sky.animal_shelter_ji22_team1_app.service.AnimalServiceImpl;
import pro.sky.animal_shelter_ji22_team1_app.service.ShelterServiceImpl;
import pro.sky.animal_shelter_ji22_team1_app.service.UserServiceImpl;
import pro.sky.animal_shelter_ji22_team1_app.service.VolunteerServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static pro.sky.animal_shelter_ji22_team1_app.constants.ShelterControllerWithMockTestConstants.*;

/**
 * Класс тестирования контроллера ShelterController с мокированием
 */
@WebMvcTest
public class ShelterControllerWithMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalRepository animalRepository;
    @SpyBean
    private AnimalServiceImpl animalServiceImpl;
    @InjectMocks
    private AnimalController animalController;

    @MockBean
    private ShelterRepository shelterRepository;
    @SpyBean
    private ShelterServiceImpl shelterServiceImpl;
    @InjectMocks
    private ShelterController shelterController;

    @MockBean
    private UserRepository userRepository;
    @SpyBean
    private UserServiceImpl userServiceImpl;
    @InjectMocks
    private UserController userController;

    @MockBean
    private VolunteerRepository volunteerRepository;
    @SpyBean
    private VolunteerServiceImpl volunteerServiceImpl;
    @InjectMocks
    private VolunteerController volunteerController;

    public ShelterControllerWithMockTest() {
    }

    @Test
    void getAllSheltersWithMockTest() throws Exception {

        ShelterEntity shelter1 = new ShelterEntity();
        shelter1.setId(SHELTER1_ID);
        shelter1.setName(SHELTER1_NAME);
        shelter1.setType(SHELTER1_TYPE);
        shelter1.setContacts(SHELTER1_CONTACTS);
        shelter1.setMediaType(SHELTER1_MEDIA_TYPE);
        shelter1.setRules(SHELTER1_RULES);

        ShelterEntity shelter2 = new ShelterEntity();
        shelter2.setId(SHELTER2_ID);
        shelter2.setName(SHELTER2_NAME);
        shelter2.setType(SHELTER2_TYPE);
        shelter2.setContacts(SHELTER2_CONTACTS);
        shelter2.setMediaType(SHELTER2_MEDIA_TYPE);
        shelter2.setRules(SHELTER2_RULES);

        List<ShelterEntity> shelterEntities = new ArrayList<>();
        shelterEntities.add(shelter1);
        shelterEntities.add(shelter2);

        JSONObject shelterObject1 = new JSONObject();
        shelterObject1.put("id", SHELTER1_ID);
        shelterObject1.put("name", SHELTER1_NAME);
        shelterObject1.put("type", SHELTER1_TYPE);
        shelterObject1.put("contacts", SHELTER1_CONTACTS);
        shelterObject1.put("mediaType", SHELTER1_MEDIA_TYPE);
        shelterObject1.put("rules", SHELTER1_RULES);

        JSONObject shelterObject2 = new JSONObject();
        shelterObject2.put("id", SHELTER2_ID);
        shelterObject2.put("name", SHELTER2_NAME);
        shelterObject2.put("type", SHELTER2_TYPE);
        shelterObject2.put("contacts", SHELTER2_CONTACTS);
        shelterObject2.put("mediaType", SHELTER2_MEDIA_TYPE);
        shelterObject2.put("rules", SHELTER2_RULES);

        JSONArray sheltersJsonArray = new JSONArray();
        sheltersJsonArray.put(shelterObject1);
        sheltersJsonArray.put(shelterObject2);

        when(shelterRepository.findAll()).thenReturn(shelterEntities);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/shelters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(sheltersJsonArray.toString()));

    }

    @Test
    void getShelterWithMockTest() throws Exception {

        ShelterEntity shelter = new ShelterEntity();
        shelter.setId(SHELTER1_ID);
        shelter.setName(SHELTER1_NAME);
        shelter.setType(SHELTER1_TYPE);
        shelter.setContacts(SHELTER1_CONTACTS);
        shelter.setMediaType(SHELTER1_MEDIA_TYPE);
        shelter.setRules(SHELTER1_RULES);

        when(shelterRepository.findById(any(Long.class))).thenReturn(Optional.of(shelter));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/shelters/" + SHELTER1_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(SHELTER1_ID))
                .andExpect(jsonPath("$.name").value(SHELTER1_NAME))
                .andExpect(jsonPath("$.type").value(SHELTER1_TYPE))
                .andExpect(jsonPath("$.contacts").value(SHELTER1_CONTACTS))
                .andExpect(jsonPath("$.mediaType").value(SHELTER1_MEDIA_TYPE))
                .andExpect(jsonPath("$.rules").value(SHELTER1_RULES));

    }

    @Test
    void getShelterLocationSchemeWithMockTest() throws Exception {

        ShelterEntity shelter = new ShelterEntity();
        shelter.setId(SHELTER1_ID);
        shelter.setName(SHELTER1_NAME);
        shelter.setType(SHELTER1_TYPE);
        shelter.setContacts(SHELTER1_CONTACTS);
        shelter.setMediaType(SHELTER1_MEDIA_TYPE);
        shelter.setRules(SHELTER1_RULES);
        shelter.setLocationSchemeData(SHELTER1_LOCATION_SCHEME);

        when(shelterRepository.findById(any(Long.class))).thenReturn(Optional.of(shelter));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/shelters/location/" + SHELTER1_ID)
                        .content(SHELTER1_LOCATION_SCHEME)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    void createShelterWithMockTest() throws Exception {

        ShelterEntity shelter = new ShelterEntity();
        shelter.setId(SHELTER1_ID);
        shelter.setName(SHELTER1_NAME);
        shelter.setType(SHELTER1_TYPE);
        shelter.setContacts(SHELTER1_CONTACTS);
        shelter.setMediaType(SHELTER1_MEDIA_TYPE);
        shelter.setRules(SHELTER1_RULES);

        JSONObject shelterObject = new JSONObject();
        shelterObject.put("id", SHELTER1_ID);
        shelterObject.put("name", SHELTER1_NAME);
        shelterObject.put("type", SHELTER1_TYPE);
        shelterObject.put("contacts", SHELTER1_CONTACTS);
        shelterObject.put("mediaType", SHELTER1_MEDIA_TYPE);
        shelterObject.put("rules", SHELTER1_RULES);

        when(shelterRepository.save(shelter)).thenReturn(shelter);
        when(shelterRepository.findById(any(Long.class))).thenReturn(Optional.of(shelter));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/shelters/location_without")
                        .content(shelterObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    void updateShelterWithMockTest() throws Exception {

        ShelterEntity shelter = new ShelterEntity();
        shelter.setId(SHELTER1_ID);
        shelter.setName(SHELTER1_NAME);
        shelter.setType(SHELTER1_TYPE);
        shelter.setContacts(SHELTER1_CONTACTS);
        shelter.setMediaType(SHELTER1_MEDIA_TYPE);
        shelter.setRules(SHELTER1_RULES);
        shelter.setLocationSchemeData(SHELTER1_LOCATION_SCHEME);

        JSONObject shelterObject = new JSONObject();
        shelterObject.put("id", SHELTER1_ID);
        shelterObject.put("name", SHELTER1_NAME);
        shelterObject.put("type", SHELTER1_TYPE);
        shelterObject.put("contacts", SHELTER1_CONTACTS);
        shelterObject.put("mediaType", SHELTER1_MEDIA_TYPE);
        shelterObject.put("rules", SHELTER1_RULES);
//        shelterObject.put("locationSchemeData", SHELTER1_LOCATION_SCHEME);

        when(shelterRepository.findById(shelter.getId())).thenReturn(Optional.of(shelter));
        when(shelterRepository.save(shelter)).thenReturn(shelter);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/shelters")
                        .content(shelterObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(SHELTER1_ID))
                .andExpect(jsonPath("$.name").value(SHELTER1_NAME))
                .andExpect(jsonPath("$.type").value(SHELTER1_TYPE))
                .andExpect(jsonPath("$.contacts").value(SHELTER1_CONTACTS))
                .andExpect(jsonPath("$.mediaType").value(SHELTER1_MEDIA_TYPE))
                .andExpect(jsonPath("$.rules").value(SHELTER1_RULES));

    }

    @Test
    void addShelterLocationSchemeWithMockTest() throws Exception {

        ShelterEntity shelter = new ShelterEntity();
        shelter.setId(SHELTER1_ID);
        shelter.setName(SHELTER1_NAME);
        shelter.setType(SHELTER1_TYPE);
        shelter.setContacts(SHELTER1_CONTACTS);
        shelter.setMediaType(SHELTER1_MEDIA_TYPE);
        shelter.setRules(SHELTER1_RULES);
        shelter.setLocationSchemeData(SHELTER1_LOCATION_SCHEME);

        when(shelterRepository.findById(shelter.getId())).thenReturn(Optional.of(shelter));
        when(shelterRepository.save(shelter)).thenReturn(shelter);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/shelters/location/" + SHELTER1_ID)
                .content(SHELTER1_LOCATION_SCHEME_FILE.getInputStream().readAllBytes())
                .accept(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.APPLICATION_JSON_VALUE));

    }

    @Test
    void deleteShelterWithMockTest() throws Exception {

        ShelterEntity shelter = new ShelterEntity();
        shelter.setId(SHELTER1_ID);
        shelter.setName(SHELTER1_NAME);
        shelter.setType(SHELTER1_TYPE);
        shelter.setContacts(SHELTER1_CONTACTS);
        shelter.setMediaType(SHELTER1_MEDIA_TYPE);
        shelter.setRules(SHELTER1_RULES);
        shelter.setLocationSchemeData(SHELTER1_LOCATION_SCHEME);

        when(shelterRepository.findById(shelter.getId())).thenReturn(Optional.of(shelter));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/shelters/" + SHELTER1_ID)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

}
