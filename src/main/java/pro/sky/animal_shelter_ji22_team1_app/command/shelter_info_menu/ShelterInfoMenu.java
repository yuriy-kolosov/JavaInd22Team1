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
       ShelterEntity shelter = shelterService.findFirst();

        return """
                Приют "%s" расположен по адресу: %s) +
                /location - Чтобы ознакомиться со схемой проезда к нему
                /shelter_contacts - Контактные данные охраны и правила оформления пропуска+
                /health_and_safety - Общие правила техники безопасности на территории приюта
                /client_contacts - Оставить контактные данные
                /volunteer - Связаться с волонтером
                """
               .formatted(shelter.getName(), shelter.getContacts());
    }
    public String location(){
        return "информация о проезде к приюту";
    }

    public String shelterContacts(){
        return shelterService.findFirst().getContacts();
    }

    public String healthAndSafety(){
        return shelterService.findFirst().getSafety_recommendations();
    }

    public String clientContacts(){
        return """
                Давайте сохраним ваши данные для будующего сотрудничества!
                /firstname - Ввести имя 
                /surname - Ввесте отчество
                /lastname - Ввести фамилию
                /phone - Ввести контактный телефон
                """;
    }
}
