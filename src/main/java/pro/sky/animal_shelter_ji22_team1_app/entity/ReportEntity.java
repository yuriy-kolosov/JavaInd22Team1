package pro.sky.animal_shelter_ji22_team1_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * Класс, определяющий структуру базы данных сервиса Report
 *
 * @author Mariia Merkel
 */
@Entity
@Table(name = "reports")
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long requestId;
    @JsonIgnore
    @Lob
    private byte[] animalPhoto;
    private String diet;
    private String health;
    private String addiction;
    private String behaviorChanges;
    private LocalDateTime reportDate;
    private String comment;

    public ReportEntity() {
    }

    public ReportEntity(Long id, Long requestId, String diet, String health, String addiction, String behaviorChanges, LocalDateTime reportDate, String comment) {
        this.id = id;
        this.requestId = requestId;
        this.diet = diet;
        this.health = health;
        this.addiction = addiction;
        this.behaviorChanges = behaviorChanges;
        this.reportDate = reportDate;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public byte[] getAnimalPhoto() {
        return animalPhoto;
    }

    public void setAnimalPhoto(byte[] animalPhoto) {
        this.animalPhoto = animalPhoto;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getAddiction() {
        return addiction;
    }

    public void setAddiction(String addiction) {
        this.addiction = addiction;
    }

    public String getBehaviorChanges() {
        return behaviorChanges;
    }

    public void setBehaviorChanges(String behaviorChanges) {
        this.behaviorChanges = behaviorChanges;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReportEntity that = (ReportEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(requestId, that.requestId) && Arrays.equals(animalPhoto, that.animalPhoto) && Objects.equals(diet, that.diet) && Objects.equals(health, that.health) && Objects.equals(addiction,
                                                                                                                                                                                                                                    that.addiction)
                && Objects.equals(behaviorChanges, that.behaviorChanges) && Objects.equals(reportDate, that.reportDate) && Objects.equals(comment, that.comment);
    }

    @Override public int hashCode() {
        int result = Objects.hash(id, requestId, diet, health, addiction, behaviorChanges, reportDate, comment);
        result = 31 * result + Arrays.hashCode(animalPhoto);
        return result;
    }

    @Override public String toString() {
        return "ReportEntity{" +
                "id=" + id +
                ", request_id=" + requestId +
                ", animal_photo=" + Arrays.toString(animalPhoto) +
                ", diet='" + diet + '\'' +
                ", health='" + health + '\'' +
                ", addiction='" + addiction + '\'' +
                ", behaviorChanges='" + behaviorChanges + '\'' +
                ", report_date=" + reportDate +
                ", comment='" + comment + '\'' +
                '}';
    }

}
