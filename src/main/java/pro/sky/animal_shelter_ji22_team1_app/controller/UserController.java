package pro.sky.animal_shelter_ji22_team1_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;
import pro.sky.animal_shelter_ji22_team1_app.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Collection<UserEntity>> getAllUsers(){
        Collection<UserEntity> users =  userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long userId){
        UserEntity user = userService.findById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserEntity user){
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping()
    public ResponseEntity<UserEntity> changeUser(@RequestBody UserEntity user){
        UserEntity
                changedUser = userService.change(user);
        return ResponseEntity.ok(changedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long userId){
        userService.delete(userId);
        return ResponseEntity.ok(userId);
    }
}
