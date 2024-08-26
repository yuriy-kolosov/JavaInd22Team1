package pro.sky.animal_shelter_ji22_team1_app.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
