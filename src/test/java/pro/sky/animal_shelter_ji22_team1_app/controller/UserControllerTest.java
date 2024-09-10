package pro.sky.animal_shelter_ji22_team1_app.controller;

import com.pengrad.telegrambot.model.User;
import netscape.javascript.JSObject;
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
import pro.sky.animal_shelter_ji22_team1_app.entity.Type;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.UserDoesNotExistException;
import pro.sky.animal_shelter_ji22_team1_app.exception.UserWithThisLoginAlreadyExistException;
import pro.sky.animal_shelter_ji22_team1_app.exception.UserWithThisPhoneAlreadyExistException;
import pro.sky.animal_shelter_ji22_team1_app.repository.UserRepository;
import pro.sky.animal_shelter_ji22_team1_app.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;
    @SpyBean
    private UserServiceImpl userService;
    @InjectMocks
    private UserController userController;

    @Test
    public void getUserById() throws Exception {
        UserEntity user = createTestUser();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(user.getId()))
                .andExpect(jsonPath("firstname").value(user.getFirstname()));
    }

    @Test
    public void getUserDoesNotExist() throws Exception {
        UserEntity user = createTestUser();
        when(userRepository.findById(anyLong())).thenThrow(UserDoesNotExistException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAllUsers() throws Exception {
        List<UserEntity> users = createTestListOfUsers();
        when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(users.get(0).getId()))
                .andExpect(jsonPath("$.[0].firstname").value(users.get(0).getFirstname()))
                .andExpect(jsonPath("$.[1].id").value(users.get(1).getId()))
                .andExpect(jsonPath("$.[1].firstname").value(users.get(1).getFirstname()));
    }

    @Test
    public void createUser() throws Exception {
        UserEntity user = createTestUser();
        JSONObject userJson = createUserJsonTest(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(userJson.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void createUserWithLoginAlreadyExist() throws Exception {
        UserEntity user = createTestUser();
        JSONObject userJson = createUserJsonTest(user);

        when(userRepository.save(any(UserEntity.class)))
                .thenThrow(UserWithThisLoginAlreadyExistException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(userJson.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserWithPhoneAlreadyExist() throws Exception {
        UserEntity user = createTestUser();
        JSONObject userJson = createUserJsonTest(user);

        when(userRepository.save(any(UserEntity.class)))
                .thenThrow(UserWithThisPhoneAlreadyExistException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(userJson.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void changeUser() throws Exception {
        UserEntity user = createTestUser();
        JSONObject userJson = createUserJsonTest(user);

        when(userRepository.save(any(UserEntity.class))).thenReturn(user);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users")
                        .content(userJson.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(user.getId()))
                .andExpect(jsonPath("firstname").value(user.getFirstname()));
    }

    @Test
    public void changeUserDoesNotExist() throws Exception {
        UserEntity user = createTestUser();
        JSONObject userJson = createUserJsonTest(user);

        when(userRepository.findById(anyLong())).thenThrow(UserDoesNotExistException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users")
                        .content(userJson.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void changeUserWithLoginAlreadyExist() throws Exception {
        UserEntity user = createTestUser();
        JSONObject userJson = createUserJsonTest(user);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.findByLogin(any(String.class)))
                .thenThrow(UserWithThisLoginAlreadyExistException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users")
                        .content(userJson.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void changeUserWithPhoneAlreadyExist() throws Exception {
        UserEntity user = createTestUser();
        JSONObject userJson = createUserJsonTest(user);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(UserEntity.class)))
                .thenThrow(UserWithThisPhoneAlreadyExistException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users")
                        .content(userJson.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteUser() throws Exception {

        UserEntity user = createTestUser();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserDoesNotExist() throws Exception {

        when(userRepository.findById(anyLong())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/1"))
                .andExpect(status().isBadRequest());
    }

    private JSONObject createUserJsonTest(UserEntity user) throws Exception {

        JSONObject userJson = new JSONObject();

        userJson.put("id", user.getId())
                .put("firstname", user.getFirstname())
                .put("surname", user.getSurname())
                .put("lastname", user.getLastname())
                .put("login", user.getLogin())
                .put("phone", user.getPhone())
                .put("comment", user.getComment())
                .put("registrationDate", user.getRegistrationDate())
                .put("type", user.getType());

        return userJson;
    }


    private UserEntity createTestUser() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setFirstname("first");
        user.setSurname("surname");
        user.setLastname("last");
        user.setLogin("login");
        user.setType(Type.NEW_CLIENT);
        user.setPhone("99999999");
        user.setChatId(12345L);
        user.setComment("comment");
        return user;
    }

    private List<UserEntity> createTestListOfUsers() {
        UserEntity user1 = new UserEntity();

        user1.setId(1L);
        user1.setFirstname("first1");
        user1.setSurname("surname1");
        user1.setLastname("last1");
        user1.setLogin("login1");
        user1.setType(Type.NEW_CLIENT);
        user1.setPhone("99999999");
        user1.setChatId(12345L);
        user1.setComment("comment1");

        UserEntity user2 = new UserEntity();

        user1.setId(2L);
        user1.setFirstname("first2");
        user1.setSurname("surname2");
        user1.setLastname("last2");
        user1.setLogin("login2");
        user1.setType(Type.NEW_CLIENT);
        user1.setPhone("88888888");
        user1.setChatId(123456L);
        user1.setComment("comment2");

        List<UserEntity> users = List.of(user1, user2);
        return users;
    }
}