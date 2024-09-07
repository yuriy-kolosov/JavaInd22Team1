package pro.sky.animal_shelter_ji22_team1_app.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RemoteControl {
    @Autowired
    private Map<String, Command> commands;

    public String start() {
        return commands.get("startCommand").execute();
    }

    public String help() {
        return commands.get("helpCommand").execute();
    }

    public String  shelter() {
        return commands.get("shelterCommand").execute();
    }

    public String  choosingAnimal() {
        return commands.get("choosingAnimalCommand").execute();
    }

    public String sendReport() {
        return commands.get("sendReportCommand").execute();
    }
}
