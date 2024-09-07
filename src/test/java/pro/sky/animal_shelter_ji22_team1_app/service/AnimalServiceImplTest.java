package pro.sky.animal_shelter_ji22_team1_app.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pro.sky.animal_shelter_ji22_team1_app.service.ConstantsForTests.ANIMALS;
import static pro.sky.animal_shelter_ji22_team1_app.service.ConstantsForTests.BOBIK;
import static pro.sky.animal_shelter_ji22_team1_app.service.ConstantsForTests.MURKA;
import static pro.sky.animal_shelter_ji22_team1_app.service.ConstantsForTests.SHARIK;

import java.util.Optional;
import org.json.JSONException;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import pro.sky.animal_shelter_ji22_team1_app.controller.AnimalController;
import pro.sky.animal_shelter_ji22_team1_app.entity.Animal;
import pro.sky.animal_shelter_ji22_team1_app.repository.AnimalRepository;

@WebMvcTest(AnimalController.class)
class AnimalServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalRepository animalRepository;

    @SpyBean
    private AnimalServiceImpl animalService;

    @InjectMocks
    private AnimalController animalController;

    private String url = "http://localhost:8080/animals";

    @Test
    void findAll() throws Exception {
        when(animalRepository.findAll()).thenReturn(ANIMALS);

        mockMvc.perform(MockMvcRequestBuilders
                                .get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(MockMvcResultHandlers.print())
               .andExpect(jsonPath("$.length()").value(3))
               .andExpect(jsonPath("$[0].id").value(ANIMALS.get(0).getId()))
               .andExpect(jsonPath("$[1].id").value(ANIMALS.get(1).getId()))
               .andExpect(jsonPath("$[2].id").value(ANIMALS.get(2).getId()))
               .andExpect(jsonPath("$[0].name").value(ANIMALS.get(0).getName()))
               .andExpect(jsonPath("$[1].name").value(ANIMALS.get(1).getName()))
               .andExpect(jsonPath("$[2].name").value(ANIMALS.get(2).getName()))
               .andExpect(jsonPath("$[0].age").value(ANIMALS.get(0).getAge()))
               .andExpect(jsonPath("$[1].age").value(ANIMALS.get(1).getAge()))
               .andExpect(jsonPath("$[2].age").value(ANIMALS.get(2).getAge()))
               .andExpect(jsonPath("$[0].breed").value(ANIMALS.get(0).getBreed()))
               .andExpect(jsonPath("$[1].breed").value(ANIMALS.get(1).getBreed()))
               .andExpect(jsonPath("$[2].breed").value(ANIMALS.get(2).getBreed()))
               .andExpect(jsonPath("$[0].regDate").value(ANIMALS.get(0).getRegDate()))
               .andExpect(jsonPath("$[1].regDate").value(ANIMALS.get(1).getRegDate()))
               .andExpect(jsonPath("$[2].regDate").value(ANIMALS.get(2).getRegDate()))
               .andExpect(jsonPath("$[0].comment").value(ANIMALS.get(0).getComment()))
               .andExpect(jsonPath("$[1].comment").value(ANIMALS.get(1).getComment()))
               .andExpect(jsonPath("$[2].comment").value(ANIMALS.get(2).getComment()));
    }

    @Test
    void findById() throws Exception {
        Animal expected = BOBIK;
        when(animalRepository.findById(any(Long.class))).thenReturn(Optional.of(expected));

        mockMvc.perform(MockMvcRequestBuilders
                                .get(url + "/1")
                                .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(MockMvcResultHandlers.print())
               .andExpect(jsonPath("$.id").value(expected.getId()))
               .andExpect(jsonPath("$.name").value(expected.getName()))
               .andExpect(jsonPath("$.age").value(expected.getAge()))
               .andExpect(jsonPath("$.breed").value(expected.getBreed()))
               .andExpect(jsonPath("$.regDate").value(expected.getRegDate()))
               .andExpect(jsonPath("$.comment").value(expected.getComment()))
        ;


    }

    @Test
    void save() throws Exception {
        Animal expected = MURKA;
        String jsonAnimal = getJsonObjectAnimal(expected);

        when(animalRepository.save(any(Animal.class))).thenReturn(expected);
        when(animalRepository.findById(any(Long.class))).thenReturn(Optional.of(expected));

        mockMvc.perform(MockMvcRequestBuilders
                                .post(url)
                                .content(jsonAnimal)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(MockMvcResultHandlers.print())
               .andExpect(jsonPath("$.id").value(expected.getId()))
               .andExpect(jsonPath("$.name").value(expected.getName()))
               .andExpect(jsonPath("$.age").value(expected.getAge()))
               .andExpect(jsonPath("$.breed").value(expected.getBreed()))
               .andExpect(jsonPath("$.regDate").value(expected.getRegDate()))
               .andExpect(jsonPath("$.comment").value(expected.getComment()));

        mockMvc.perform(MockMvcRequestBuilders
                                .get(url + "/1")
                                .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(MockMvcResultHandlers.print())
               .andExpect(jsonPath("$.id").value(expected.getId()))
               .andExpect(jsonPath("$.name").value(expected.getName()))
               .andExpect(jsonPath("$.age").value(expected.getAge()))
               .andExpect(jsonPath("$.breed").value(expected.getBreed()))
               .andExpect(jsonPath("$.regDate").value(expected.getRegDate()))
               .andExpect(jsonPath("$.comment").value(expected.getComment()));
    }

    private String getJsonObjectAnimal(Animal animal) throws JSONException {
        JSONObject jsonAnimal = new JSONObject();
        jsonAnimal.put("id", animal.getId());
        jsonAnimal.put("name", animal.getName());
        jsonAnimal.put("age", animal.getAge());
        jsonAnimal.put("breed", animal.getBreed());
        jsonAnimal.put("regDate", "2024-06-08T12:30:00");
        jsonAnimal.put("comment", animal.getComment());

        return jsonAnimal.toString();
    }

    @Test
    void change() throws Exception {
        Animal expected = SHARIK;
        String jsonAnimal = getJsonObjectAnimal(expected);

        when(animalRepository.findById(3L)).thenReturn(Optional.of(expected));
        when(animalRepository.save(any(Animal.class))).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders
                                .put(url)
                                .content(jsonAnimal)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(expected.getId()))
               .andExpect(jsonPath("$.name").value(expected.getName()))
               .andExpect(jsonPath("$.age").value(expected.getAge()))
               .andExpect(jsonPath("$.breed").value(expected.getBreed()))
               .andExpect(jsonPath("$.regDate").value(expected.getRegDate()))
               .andExpect(jsonPath("$.comment").value(expected.getComment()));
    }

    @Test
    void delete() throws Exception {
        when(animalRepository.findById(3L)).thenReturn(Optional.of(SHARIK));
        doNothing().when(animalRepository).delete(any(Animal.class));

        mockMvc.perform(MockMvcRequestBuilders
                                .delete(url + "/3")
                                .accept(MediaType.APPLICATION_JSON))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").value(SHARIK.getId()));
    }
}