package pro.sky.animal_shelter_ji22_team1_app.exception;

public class ErrorDto {
    private String message;

    public ErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
