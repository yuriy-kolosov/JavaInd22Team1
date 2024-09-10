package pro.sky.animal_shelter_ji22_team1_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VolunteerWithThisPhoneAlreadyExistException extends RuntimeException{
    public VolunteerWithThisPhoneAlreadyExistException(String message) {
        super(message);
    }
}
