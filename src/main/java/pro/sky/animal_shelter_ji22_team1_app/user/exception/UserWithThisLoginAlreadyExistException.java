package pro.sky.animal_shelter_ji22_team1_app.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserWithThisLoginAlreadyExistException extends RuntimeException{
    public UserWithThisLoginAlreadyExistException(String message) {
        super(message);
    }
}
