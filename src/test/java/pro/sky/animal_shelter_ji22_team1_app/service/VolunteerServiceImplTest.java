package pro.sky.animal_shelter_ji22_team1_app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.animal_shelter_ji22_team1_app.entity.VolunteerEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.VolunteerDoesNotExistException;
import pro.sky.animal_shelter_ji22_team1_app.exception.VolunteerWithThisLoginAlreadyExistException;
import pro.sky.animal_shelter_ji22_team1_app.exception.VolunteerWithThisPhoneAlreadyExistException;
import pro.sky.animal_shelter_ji22_team1_app.repository.VolunteerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VolunteerServiceImplTest {
    @InjectMocks
    private VolunteerServiceImpl volunteerService;

    @Mock
    private VolunteerRepository volunteerRepository;


    @Test
    public void shouldFindByVolunteerId() {
        VolunteerEntity volunteer = createTestVolunteer();
        when(volunteerRepository.findById(anyLong())).thenReturn(Optional.of(volunteer));

        assertEquals(volunteerService.findById(1L), volunteer);
    }

    @Test
    public void shouldFindByVolunteerIdVolunteerDoesNotExist() {
        when(volunteerRepository.findById(anyLong())).thenThrow(VolunteerDoesNotExistException.class);

        assertThrows(VolunteerDoesNotExistException.class, () -> volunteerService.findById(1L),
                "Волонтёра с id = 1 не существует");
    }

    @Test
    void shouldFindAllVolunteers() {

        List<VolunteerEntity> volunteers = createTestListOfVolunteers();

        when(volunteerRepository.findAll()).thenReturn(volunteers);

        assertEquals(volunteers, volunteerService.findAll());
    }

    @Test
    public void findAllVolunteersFromEmpty() {
        when(volunteerRepository.findAll()).thenReturn(new ArrayList<VolunteerEntity>());

        assertTrue(volunteerService.findAll().isEmpty());
    }

    @Test
    public void saveVolunteerWithLoginAlreadyExist() {
        VolunteerEntity volunteer = createTestVolunteer();

        when(volunteerRepository.findByLogin(volunteer.getLogin())).thenThrow(VolunteerWithThisLoginAlreadyExistException.class);

        assertThrows(VolunteerWithThisLoginAlreadyExistException.class, () -> volunteerService.save(volunteer),
                "Волонтёра с логином %s уже существует".formatted(volunteer.getLogin()));
    }

    @Test
    public void saveVolunteerWithPhoneAlreadyExist() {
        VolunteerEntity volunteer = createTestVolunteer();

        when(volunteerRepository.findByPhone(volunteer.getPhone())).thenThrow(VolunteerWithThisPhoneAlreadyExistException.class);

        assertThrows(VolunteerWithThisPhoneAlreadyExistException.class, () -> volunteerService.save(volunteer),
                "Волонтёр с номером телефона %s уже существует".formatted(volunteer.getPhone()));
    }

    @Test
    public void changeVolunteer() {
        VolunteerEntity volunteer = createTestVolunteer();

        when(volunteerRepository.findById(volunteer.getId())).thenReturn(Optional.of(volunteer));

        assertEquals(volunteer, volunteerService.change(volunteer));
    }

    @Test
    public void changeVolunteerWithDoesNotExistId() {
        VolunteerEntity volunteer = createTestVolunteer();

        when(volunteerRepository.findById(volunteer.getId())).thenThrow(VolunteerDoesNotExistException.class);

        assertThrows(VolunteerDoesNotExistException.class, () -> volunteerService.change(volunteer),
                "Волонтёра с id = %d не существует".formatted(volunteer.getId()));
    }

    @Test
    public void changeVolunteerWithLoginAlreadyExist() {
        VolunteerEntity volunteer = createTestVolunteer();

        when(volunteerRepository.findById(volunteer.getId())).thenReturn(Optional.of(volunteer));

        when(volunteerRepository.findByLogin(volunteer.getLogin())).thenThrow(VolunteerWithThisLoginAlreadyExistException.class);

        assertThrows(VolunteerWithThisLoginAlreadyExistException.class, () -> volunteerService.change(volunteer),
                "Волонтёр с логином %s уже существует".formatted(volunteer.getLogin()));
    }

    @Test
    public void changeVolunteerWithPhoneAlreadyExist() {
        VolunteerEntity volunteer = createTestVolunteer();

        when(volunteerRepository.findById(volunteer.getId())).thenReturn(Optional.of(volunteer));

        when(volunteerRepository.findByPhone(volunteer.getPhone())).thenThrow(VolunteerWithThisPhoneAlreadyExistException.class);

        assertThrows(VolunteerWithThisPhoneAlreadyExistException.class, () -> volunteerService.change(volunteer),
                "Волонтёр с номером телефона %s уже существует".formatted(volunteer.getPhone()));
    }

    @Test
    public void deleteVolunteerDoesNotExist() {
        when(volunteerRepository.findById(anyLong())).thenThrow(VolunteerDoesNotExistException.class);

        assertThrows(VolunteerDoesNotExistException.class, () -> volunteerService.delete(1L),
                "Волонтёра с id = 1 не существует");
    }

    private VolunteerEntity createTestVolunteer() {
        VolunteerEntity volunteer = new VolunteerEntity();
        volunteer.setId(1L);
        volunteer.setFirstname("first");
        volunteer.setSurname("surname");
        volunteer.setLastname("last");
        volunteer.setLogin("login");
        volunteer.setPhone("99999999");
        volunteer.setChatId(12345L);
        volunteer.setComment("comment");
        return volunteer;
    }

    private List<VolunteerEntity> createTestListOfVolunteers() {
        VolunteerEntity volunteer1 = new VolunteerEntity();

        volunteer1.setId(1L);
        volunteer1.setFirstname("first1");
        volunteer1.setSurname("surname1");
        volunteer1.setLastname("last1");
        volunteer1.setLogin("login1");
        volunteer1.setPhone("99999999");
        volunteer1.setChatId(12345L);
        volunteer1.setComment("comment1");

        VolunteerEntity volunteer2 = new VolunteerEntity();

        volunteer1.setId(2L);
        volunteer1.setFirstname("first2");
        volunteer1.setSurname("surname2");
        volunteer1.setLastname("last2");
        volunteer1.setLogin("login2");
        volunteer1.setPhone("88888888");
        volunteer1.setChatId(123456L);
        volunteer1.setComment("comment2");

        List<VolunteerEntity> volunteers = List.of(volunteer1, volunteer2);
        return volunteers;
    }
}