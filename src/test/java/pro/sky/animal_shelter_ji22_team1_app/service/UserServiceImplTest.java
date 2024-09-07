package pro.sky.animal_shelter_ji22_team1_app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.animal_shelter_ji22_team1_app.entity.Type;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.UserDoesNotExistException;
import pro.sky.animal_shelter_ji22_team1_app.exception.UserWithThisLoginAlreadyExistException;
import pro.sky.animal_shelter_ji22_team1_app.exception.UserWithThisPhoneAlreadyExistException;
import pro.sky.animal_shelter_ji22_team1_app.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;


    @Test
    public void shouldFindByUserId() {
        UserEntity user = createTestUser();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        assertEquals(userService.findById(1L), user);
    }

    @Test
    public void shouldFindByUserIdUserDoesNotExist() {
        when(userRepository.findById(anyLong())).thenThrow(UserDoesNotExistException.class);

        assertThrows(UserDoesNotExistException.class, () -> userService.findById(1L),
                "Пользователя с id = 1 не существует");
    }

    @Test
    void shouldFindAllUsers() {

        List<UserEntity> users = createTestListOfUsers();

        when(userRepository.findAll()).thenReturn(users);

        assertEquals(users, userService.findAll());
    }

    @Test
    public void findAllUsersFromEmpty() {
        when(userRepository.findAll()).thenReturn(new ArrayList<UserEntity>());

        assertTrue(userService.findAll().isEmpty());
    }

    @Test
    public void saveUserWithLoginAlreadyExist() {
        UserEntity user = createTestUser();

        when(userRepository.findByLogin(user.getLogin())).thenThrow(UserWithThisLoginAlreadyExistException.class);

        assertThrows(UserWithThisLoginAlreadyExistException.class, () -> userService.save(user),
                "Пользователь с логином %s уже существует".formatted(user.getLogin()));
    }

    @Test
    public void saveUserWithPhoneAlreadyExist() {
        UserEntity user = createTestUser();

        when(userRepository.findByPhone(user.getPhone())).thenThrow(UserWithThisPhoneAlreadyExistException.class);

        assertThrows(UserWithThisPhoneAlreadyExistException.class, () -> userService.save(user),
                "Пользователь с номером телефона %s уже существует".formatted(user.getPhone()));
    }

    @Test
    public void changeUserWithDoesNotExistId() {
        UserEntity user = createTestUser();

        when(userRepository.findById(user.getId())).thenThrow(UserDoesNotExistException.class);

        assertThrows(UserDoesNotExistException.class, () -> userService.change(user),
                "Пользователя с id = %d не существует".formatted(user.getId()));
    }

    @Test
    public void changeUserWithLoginAlreadyExist() {
        UserEntity user = createTestUser();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        when(userRepository.findByLogin(user.getLogin())).thenThrow(UserWithThisLoginAlreadyExistException.class);

        assertThrows(UserWithThisLoginAlreadyExistException.class, () -> userService.change(user),
                "Пользователь с логином %s уже существует".formatted(user.getLogin()));
    }

    @Test
    public void changeUserWithPhoneAlreadyExist() {
        UserEntity user = createTestUser();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        when(userRepository.findByPhone(user.getPhone())).thenThrow(UserWithThisPhoneAlreadyExistException.class);

        assertThrows(UserWithThisPhoneAlreadyExistException.class, () -> userService.change(user),
                "Пользователь с номером телефона %s уже существует".formatted(user.getPhone()));
    }

    @Test
    public void deleteUserDoesNotExist(){
        when(userRepository.findById(anyLong())).thenThrow(UserDoesNotExistException.class);

        assertThrows(UserDoesNotExistException.class, ()->userService.delete(1L),
                "Пользователя с id = 1 не существует");
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