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

    public String shelterInfo() {
        return commands.get("shelterInfoCommand").execute();
    }

    public String entry() {
        return commands.get("entryCommand").execute();
    }

    public String pets() {
        return commands.get("petsCommand").execute();
    }

    public String rules() {
        return commands.get("rulesCommand").execute();
    }

    public String dailyReportForm() {
        return commands.get("dailyReportFormCommand").execute();
    }
    public String location(){
        return commands.get("locationCommand").execute();
    }
    public String shelterContacts(){
        return commands.get("shelterContactsCommand").execute();
    }
    public String heathAndSafety(){
        return commands.get("healthAndSafetyCommand").execute();
    }
    public String clientContacts(){
        return commands.get("clientContactsCommand").execute();
    }
}
