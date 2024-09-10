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
import pro.sky.animal_shelter_ji22_team1_app.entity.VolunteerEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.ErrorDto;
import pro.sky.animal_shelter_ji22_team1_app.service.VolunteerService;

import java.util.Collection;

@RestController
@RequestMapping("volunteer")
public class VolunteerController {
    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Operation(
            summary = "Вывод всех волонтёров приюта",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные волонтёры",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = VolunteerEntity.class))
                            )
                    )
            },
            tags = "volunteer"
    )
    @GetMapping
    public ResponseEntity<Collection<VolunteerEntity>> getAllVolunteers() {
        Collection<VolunteerEntity> volunteer = volunteerService.findAll();
        return ResponseEntity.ok(volunteer);
    }

    @Operation(
            summary = "Вывод волонтёра приюта по идентификатору",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденный волонтёр",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = VolunteerEntity.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Волонтёр не найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            },
            tags = "volunteer"
    )
    @GetMapping("/{volunteerId}")
    public ResponseEntity<VolunteerEntity> getVolunteer(@PathVariable Long volunteerId) {
        VolunteerEntity volunteer = volunteerService.findById(volunteerId);
        return ResponseEntity.ok(volunteer);
    }

    @Operation(
            summary = "Создание нового волонтёра",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Создаваемый волонтёр",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = VolunteerEntity.class)
                    )
            ),
            tags = "volunteer"
    )
    @PostMapping
    public ResponseEntity<Void> createVolunteer(@RequestBody VolunteerEntity volunteer) {
        volunteerService.save(volunteer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Изменение волонтёра по идентификатору",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Редактируемый волонтёр",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = VolunteerEntity.class)
                    )
            ),
            tags = "volunteer"
    )
    @PutMapping()
    public ResponseEntity<VolunteerEntity> changeVolunteer(@RequestBody VolunteerEntity volunteer) {
        VolunteerEntity changedVolunteer = volunteerService.change(volunteer);
        return ResponseEntity.ok(changedVolunteer);
    }

    @Operation(
            summary = "Удаление волонтёра",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Волонтёр удален",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Long.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Волонтёр не найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            },
            tags = "volunteer"
    )
    @DeleteMapping("/{volunteerId}")
    public ResponseEntity<Long> deleteVolunteer(@PathVariable Long volunteerId) {
        volunteerService.delete(volunteerId);
        return ResponseEntity.ok(volunteerId);
    }
}
