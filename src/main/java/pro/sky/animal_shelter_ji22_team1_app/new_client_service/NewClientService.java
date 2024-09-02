package pro.sky.animal_shelter_ji22_team1_app.new_client_service;

import pro.sky.animal_shelter_ji22_team1_app.entity.Type;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;

import java.time.LocalDateTime;

public interface NewClientService {

    String getShelterAboutText(Long shelterId);

    byte[] getShelterLocationScheme(Long shelterId);

    UserEntity createNewClient(String firstName, String secondName, String login, String phone, LocalDateTime localDateTime);

    UserEntity addLastNameToNewClient(UserEntity user, String lastName);

    UserEntity addTypeToNewClient(UserEntity user, Type type);

    UserEntity addCommentToNewClient(UserEntity user, String comment);

    UserEntity addChatIdToNewClient(UserEntity user, Long chatId);

    void putNewClientToDb(UserEntity user);

}
