package pro.sky.animal_shelter_ji22_team1_app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс, определяющий структуру таблицы volunteers
 *
 * @author mariia_merkel
 */
@Entity
@Table(name = "volunteers")
public class VolunteerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "f_name")
    private String firstname;

    @Column(name = "s_name")
    private String surname;

    @Column(name = "l_name")
    private String lastname;

    private String login;

    private String phone;

    @Column(name = "reg_date")
    private LocalDateTime registrationDate;

    private String comment;

    @Column(name = "chat_id")
    private Long chatId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolunteerEntity that = (VolunteerEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(firstname, that.firstname) && Objects.equals(surname, that.surname) && Objects.equals(lastname, that.lastname) && Objects.equals(login, that.login) && Objects.equals(phone, that.phone) && Objects.equals(registrationDate, that.registrationDate) && Objects.equals(comment, that.comment) && Objects.equals(chatId, that.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, surname, lastname, login, phone, registrationDate, comment, chatId);
    }
}
