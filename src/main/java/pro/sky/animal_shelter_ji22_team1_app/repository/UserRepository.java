package pro.sky.animal_shelter_ji22_team1_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByLogin(String login);
    List<UserEntity> findByPhone(Long phone);
}
