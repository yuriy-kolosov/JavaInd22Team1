package pro.sky.animal_shelter_ji22_team1_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReportDoesNotExistException extends RuntimeException {

    public ReportDoesNotExistException(String message) {
        super(message);
    }
}
