package pro.sky.animal_shelter_ji22_team1_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

/**
 * Класс, определяющий структуру базы данных сервиса Shelter
 *
 * @author yuriy_kolosov
 */
@Entity
@Table(name = "shelters")
public class ShelterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AnimalType type;

    private String address;
    private String schedule;
    private String contacts;
    private String rules;
    private String safetyRecommendations;

    @Column(name = "media_type")
    private String mediaType;

    @JsonIgnore
    @Column(name = "location_scheme")
    private byte[] locationSchemeData;

    public ShelterEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getSafetyRecommendations() {
        return safetyRecommendations;
    }

    public void setSafetyRecommendations(String safetyRecommendations) {
        this.safetyRecommendations = safetyRecommendations;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getLocationSchemeData() {
        return locationSchemeData;
    }

    public void setLocationSchemeData(byte[] locationSchemeData) {
        this.locationSchemeData = locationSchemeData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelterEntity shelter = (ShelterEntity) o;
        return Objects.equals(id, shelter.id) && Objects.equals(name, shelter.name) && type == shelter.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }

    @Override
    public String toString() {
        return "ShelterEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

}
