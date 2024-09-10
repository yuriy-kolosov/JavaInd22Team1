package pro.sky.animal_shelter_ji22_team1_app.command;

public class HealthAndSafetyCommand implements Command {
    private final Menu menu;

    public HealthAndSafetyCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String execute() {
        return menu.healthAndSafety();
    }
}
