package pro.sky.animal_shelter_ji22_team1_app.exception;

public class UserWithThisPhoneAlreadyExistException extends RuntimeException{
    public UserWithThisPhoneAlreadyExistException(String message) {
        super(message);
    }
}
