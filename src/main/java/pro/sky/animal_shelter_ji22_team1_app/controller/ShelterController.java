package pro.sky.animal_shelter_ji22_team1_app.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.animal_shelter_ji22_team1_app.entity.ShelterEntity;
import pro.sky.animal_shelter_ji22_team1_app.service.ShelterService;

import java.util.Collection;

/**
 * Контроллер, управляющий администрированием сервиса Shelter ("Приют")
 * @author Юрий
 */
@RestController
@RequestMapping("/shelter")
public class ShelterController {

    public final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Вывод информации о всех приютах из базы данных",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShelterEntity[].class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<Collection<ShelterEntity>> getAllShelters() {
        Collection<ShelterEntity> shelters = shelterService.findAllShelters();
        return ResponseEntity.ok(shelters);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Вывод информации о приюте с указанным номером (id) из базы данных",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShelterEntity.class)
                    )
            )
    })
    @GetMapping("/{shelterId}")
    public ResponseEntity<ShelterEntity> getShelter(@PathVariable Long shelterId) {
        ShelterEntity shelters = shelterService.findShelterById(shelterId);
        return ResponseEntity.ok(shelters);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись информации о новом приюте в базу данных"
            )
    })
    @PostMapping
    public ResponseEntity<Void> createShelter(@RequestBody ShelterEntity shelter) {
        shelterService.saveShelter(shelter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Изменение информации о приюте с указанным номером (id) в базе данных",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShelterEntity.class)
                    )
            )
    })
    @PutMapping()
    public ResponseEntity<ShelterEntity> changeShelter(@RequestBody ShelterEntity shelter) {
        ShelterEntity changedShelter = shelterService.changeShelter(shelter);
        return ResponseEntity.ok(changedShelter);
    }


    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление всей информации о приюте с указанным номером (id) в базе данных"
            )
    })
    @DeleteMapping("/{shelterId}")
    public ResponseEntity<Long> deleteShelter(@PathVariable Long shelterId) {
        shelterService.deleteShelter(shelterId);
        return ResponseEntity.ok(shelterId);
    }

}
