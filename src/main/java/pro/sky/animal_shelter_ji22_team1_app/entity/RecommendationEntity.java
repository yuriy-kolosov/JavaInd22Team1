package pro.sky.animal_shelter_ji22_team1_app.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "recommendations")
public class RecommendationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AnimalType type;

    @Enumerated(EnumType.STRING)
    private TitleType title;

    private String description;

    public RecommendationEntity() {
    }

    public RecommendationEntity(Long id, AnimalType type, TitleType title, String description) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public TitleType getTitle() {
        return title;
    }

    public void setTitle(TitleType title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendationEntity that = (RecommendationEntity) o;
        return Objects.equals(id, that.id) && type == that.type && title == that.title && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, title, description);
    }

    @Override
    public String toString() {
        return "RecommendationEntity{" +
                "id=" + id +
                ", type=" + type +
                ", title=" + title +
                ", description='" + description + '\'' +
                '}';
    }

}
