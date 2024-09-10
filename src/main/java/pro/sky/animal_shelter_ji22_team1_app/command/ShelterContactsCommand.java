package pro.sky.animal_shelter_ji22_team1_app.command;

public class ShelterContactsCommand implements Command {
    private final Menu menu;

    public ShelterContactsCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.shelterContacts();
    }
}
