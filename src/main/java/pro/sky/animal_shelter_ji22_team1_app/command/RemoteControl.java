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

    public String dog() {
        return commands.get("dogCommand").execute();
    }

    public String cat() {
        return commands.get("catCommand").execute();
    }

    public String dogs() {
        return commands.get("dogsCommand").execute();
    }

    public String cats() {
        return commands.get("catsCommand").execute();
    }

    public String documents() {
        return commands.get("documentsCommand").execute();
    }

    public String transportation() {
        return commands.get("transportationCommand").execute();
    }

    public String petHouse() {
        return commands.get("petHouseCommand").execute();
    }

    public String invalidPetHouse() {
        return commands.get("invalidPetHouseCommand").execute();
    }

    public String waiverList() {
        return commands.get("waiverListCommand").execute();
    }

    public String dogRules() {
        return commands.get("dogRulesCommand").execute();
    }

    public String puppyHouse() {
        return commands.get("puppyHouseCommand").execute();
    }

    public String dogHandler() {
        return commands.get("dogHandlerCommand").execute();
    }

    public String dogHandlerList() {
        return commands.get("dogHandlerListCommand").execute();
    }

    public String catRules() {
        return commands.get("catRulesCommand").execute();
    }

    public String dailyReportForm() {
        return commands.get("dailyReportFormCommand").execute();
    }

    public String location() {
        return commands.get("locationCommand").execute();
    }

    public String shelterContacts() {
        return commands.get("shelterContactsCommand").execute();
    }

    public String heathAndSafety() {
        return commands.get("healthAndSafetyCommand").execute();
    }

    public String clientContacts() {
        return commands.get("clientContactsCommand").execute();
    }

}
