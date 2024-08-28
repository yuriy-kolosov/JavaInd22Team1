package pro.sky.animal_shelter_ji22_team1_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Класс, определяющий структуру базы данных сервиса Shelter
 * @author yuriy_kolosov
 */
@Entity
@Table(name = "shelters")
public class ShelterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String contacts;

    @Column(name = "media_type")
    private String mediaType;

    private String rules;

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

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
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
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
