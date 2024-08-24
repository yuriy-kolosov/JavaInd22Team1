package pro.sky.animal_shelter_ji22_team1_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByLogin(String login);
    List<UserEnity> findByPhone(String phone);
}
