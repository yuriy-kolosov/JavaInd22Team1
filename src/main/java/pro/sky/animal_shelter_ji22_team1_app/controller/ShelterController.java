package pro.sky.animal_shelter_ji22_team1_app.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.animal_shelter_ji22_team1_app.entity.ShelterEntity;
import pro.sky.animal_shelter_ji22_team1_app.service.ShelterService;

import java.io.IOException;
import java.util.Collection;

/**
 * Контроллер, управляющий администрированием сервиса Shelter ("Приют")
 *
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
    public ResponseEntity<ShelterEntity> getShelter(@Parameter(name = "Уникальный номер приюта"
            , example = "1") @PathVariable Long shelterId) {
        ShelterEntity shelters = shelterService.findShelterById(shelterId);
        return ResponseEntity.ok(shelters);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись информации о новом приюте в базу данных"
            )
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ShelterEntity> createShelter(@Parameter(name = "Все данные приюта, кроме схемы проезда") @RequestBody ShelterEntity shelter
            , @Parameter(name = "Файл .jpeg со схемой проезда") @RequestParam MultipartFile shelterLocationSchemeFile) throws IOException {
        ShelterEntity postedShelter = shelterService.saveShelter(shelter, shelterLocationSchemeFile);
        return ResponseEntity.ok(postedShelter);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Запись информации о схеме размещения (проезда) приюта в базу данных"
            )
    })
    @PostMapping(value = "/location{shelterId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ShelterEntity> postShelterLocationScheme(@Parameter(name = "Уникальный номер приюта"
            , example = "1") @PathVariable Long shelterId
            , @Parameter(name = "Файл .jpeg со схемой проезда") @RequestParam MultipartFile shelterLocationSchemeFile) throws IOException {
        shelterService.saveShelterLocationScheme(shelterId, shelterLocationSchemeFile);
        ShelterEntity shelter = shelterService.findShelterById(shelterId);
        return ResponseEntity.ok(shelter);
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
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ShelterEntity> updateShelter(@Parameter(name = "Все данные приюта, кроме схемы проезда") @RequestBody ShelterEntity shelter
            , @Parameter(name = "Файл .jpeg со схемой проезда") @RequestParam MultipartFile shelterLocationSchemeFile) throws IOException {
        ShelterEntity changedShelter = shelterService.changeShelter(shelter, shelterLocationSchemeFile);
        return ResponseEntity.ok(changedShelter);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление всей информации о приюте с указанным номером (id) в базе данных"
            )
    })
    @DeleteMapping("/{shelterId}")
    public ResponseEntity<Long> deleteShelter(@Parameter(name = "Уникальный номер приюта"
            , example = "1") @PathVariable Long shelterId) {
        shelterService.deleteShelter(shelterId);
        return ResponseEntity.ok(shelterId);
    }

}
