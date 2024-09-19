package pro.sky.animal_shelter_ji22_team1_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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
    @JsonIgnore
    private byte[] animalPhoto;
    private String diet;
    private String mediaType;
    private String general;
    private String behavior;
    private LocalDateTime reportDate;
    private boolean isAccepted;
    private String comment;
    private boolean isSent;

    @ManyToOne
    private UserEntity user;

    public ReportEntity() {
    }

    public ReportEntity(Long id
            , byte[] animalPhoto
            , String diet
            , String mediaType
            , String general
            , String behavior
            , LocalDateTime reportDate
            , boolean isAccepted
            , String comment
            , boolean isSent
            , UserEntity user) {
        this.id = id;
        this.animalPhoto = animalPhoto;
        this.diet = diet;
        this.mediaType = mediaType;
        this.general = general;
        this.behavior = behavior;
        this.reportDate = reportDate;
        this.isAccepted = isAccepted;
        this.comment = comment;
        this.isSent = isSent;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportEntity that = (ReportEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ReportEntity{" +
                "id=" + id +
                ", animalPhoto=" + Arrays.toString(animalPhoto) +
                ", diet='" + diet + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", general='" + general + '\'' +
                ", behavior='" + behavior + '\'' +
                ", reportDate=" + reportDate +
                ", isAccepted=" + isAccepted +
                ", comment='" + comment + '\'' +
                ", isSent=" + isSent +
                ", user=" + user +
                '}';
    }

}
