package pro.sky.animal_shelter_ji22_team1_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.ErrorDto;
import pro.sky.animal_shelter_ji22_team1_app.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Вывод всех клиентов приюта",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные клиенты",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = UserEntity.class))
                            )
                    )
            },
            tags = "users"
    )
    @GetMapping
    public ResponseEntity<Collection<UserEntity>> getAllUsers() {
        Collection<UserEntity> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "Вывод клиента приюта по идентификатору",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденный клиент",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserEntity.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Клиент не найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            },
            tags = "users"
    )
    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long userId) {
        UserEntity user = userService.findById(userId);
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Создание нового клиента",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Создаваемый клиент",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserEntity.class)
                    )
            ),
            tags = "users"
    )
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserEntity user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Изменение клиента по идентификатору",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Редактируемый клиент",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserEntity.class)
                    )
            ),
            tags = "users"
    )
    @PutMapping()
    public ResponseEntity<UserEntity> changeUser(@RequestBody UserEntity user) {
        UserEntity changedUser = userService.change(user);
        return ResponseEntity.ok(changedUser);
    }

    @Operation(
            summary = "Удаление клиента",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Клиент удален",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Long.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Клиент не найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            },
            tags = "users"
    )
    @DeleteMapping("/{userId}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok(userId);
    }
}
