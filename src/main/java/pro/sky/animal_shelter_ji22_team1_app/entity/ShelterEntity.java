package pro.sky.animal_shelter_ji22_team1_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

/**
 * Класс, определяющий структуру базы данных сервиса Shelter
 * @author yuriy_kolosov
 */
@Entity
@Table(name = "shelter")
public class ShelterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String contacts;

    private String mediaType;

    @Lob
    @JsonIgnore
    private byte[] rulesData;

    @Lob
    @JsonIgnore
    private byte[] locationSchemeData;

    public ShelterEntity() {
    }

    public ShelterEntity(Long id, String name, String type, String contacts, String mediaType) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.contacts = contacts;
        this.mediaType = mediaType;
    }

    public ShelterEntity(Long id, String name, String type, String contacts, String mediaType, byte[] rulesData, byte[] locationSchemeData) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.contacts = contacts;
        this.mediaType = mediaType;
        this.rulesData = rulesData;
        this.locationSchemeData = locationSchemeData;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getRulesData() {
        return rulesData;
    }

    public void setRulesData(byte[] rulesData) {
        this.rulesData = rulesData;
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
        ShelterEntity that = (ShelterEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(contacts, that.contacts) && Objects.equals(mediaType, that.mediaType) && Objects.deepEquals(rulesData, that.rulesData) && Objects.deepEquals(locationSchemeData, that.locationSchemeData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, contacts, mediaType, Arrays.hashCode(rulesData), Arrays.hashCode(locationSchemeData));
    }

}
