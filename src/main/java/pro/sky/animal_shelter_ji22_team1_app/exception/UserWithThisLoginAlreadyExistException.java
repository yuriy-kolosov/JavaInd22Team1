package pro.sky.animal_shelter_ji22_team1_app.exception;

public class UserWithThisLoginAlreadyExistException extends RuntimeException{
    public UserWithThisLoginAlreadyExistException(String message) {
        super(message);
    }
}
