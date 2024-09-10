package pro.sky.animal_shelter_ji22_team1_app.command;

public class LocationCommand implements Command {
    private final Menu menu;

    public LocationCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.location();
    }
}
