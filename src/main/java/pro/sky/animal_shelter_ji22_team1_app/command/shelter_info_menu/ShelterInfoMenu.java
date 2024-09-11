package pro.sky.animal_shelter_ji22_team1_app.command.shelter_info_menu;

import org.springframework.stereotype.Component;
import pro.sky.animal_shelter_ji22_team1_app.entity.ShelterEntity;
import pro.sky.animal_shelter_ji22_team1_app.service.ShelterService;

@Component
public class ShelterInfoMenu {
    private final ShelterService shelterService;

    public ShelterInfoMenu(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    public String shelterInfo() {
//        ShelterEntity shelter = shelterService.findShelterById(0L);

        return """
                Приют "вызов из бд" расположен по адресу: вызов из бд) +
                /location - Чтобы ознакомиться со схемой проезда к нему
                /shelter_contacts - Контактные данные охраны и правила оформления пропуска+
                /health_and_safety - Общие правила техники безопасности на территории приюта
                /client_contacts - Оставить контактные данные"+
                /volunteer - связаться с волонтером
                """;
//                .formatted(shelter.getName(), shelter.getContacts());
    }
    public String location(){
        return "информация о проезде к приюту";
    }

    public String shelterContacts(){
        return "контактные данные охраны и схемы проезда";
    }

    public String healthAndSafety(){
        return "информация о технике безопасности";
    }

    public String clientContacts(){
        return "запись контактной информации";
    }
}
