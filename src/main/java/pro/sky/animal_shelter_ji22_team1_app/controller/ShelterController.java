package pro.sky.animal_shelter_ji22_team1_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.animal_shelter_ji22_team1_app.entity.ShelterEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.ErrorDto;
import pro.sky.animal_shelter_ji22_team1_app.service.ShelterService;

import java.io.IOException;
import java.util.Collection;

/**
 * Контроллер, управляющий администрированием сервиса Shelter ("Приют для домашних животных")
 *
 * @author yuriy_kolosov
 */
@RestController
@RequestMapping("/shelters")
public class ShelterController {

    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    Logger logger = LoggerFactory.getLogger(ShelterController.class);

    @Operation(
            summary = "Вывод информации о всех приютах из базы данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные приюты",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ShelterEntity.class))
                            )
                    )
            },
            tags = "shelters"
    )
    @GetMapping
    public ResponseEntity<Collection<ShelterEntity>> getAllShelters() {

        logger.debug("\"Get\" getAllShelters method was invoke...");

        Collection<ShelterEntity> shelters = shelterService.findAllShelters();
        return ResponseEntity.ok(shelters);
    }

    @Operation(
            summary = "Вывод информации о приюте с указанным номером (id) из базы данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найден приют",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ShelterEntity.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Приют не найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            },
            tags = "shelters"
    )
    @GetMapping("/{shelterId}")
    public ResponseEntity<ShelterEntity> getShelter(@PathVariable Long shelterId) {

        logger.debug("\"Get\" getShelter method was invoke...");

        ShelterEntity shelter = shelterService.findShelterById(shelterId);
        return ResponseEntity.ok(shelter);
    }

    @Operation(
            summary = "Вывод информации о приюте из базы данных: адрес (схема проезда)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найден адрес приюта (схема проезда)",
                            content = @Content(
                                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                    schema = @Schema(implementation = ShelterEntity.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Адрес приюта (схема приезда) не найдена",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            },
            tags = "shelters"
    )
    @GetMapping("/location/{shelterId}")
    public ResponseEntity<byte[]> getShelterLocationScheme(@PathVariable Long shelterId) {

        logger.debug("\"Get\" getShelterLocationScheme method was invoke...");

        ShelterEntity shelter = shelterService.findShelterById(shelterId);

        byte[] schemeFile = shelter.getLocationSchemeData();
        if (schemeFile != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(shelter.getMediaType()));
            headers.setContentLength(schemeFile.length);
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(shelter.getLocationSchemeData());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(
            summary = "Запись информации о новом приюте в базу данных (без схемы проезда)",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Новый приют",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShelterEntity.class)
                    )
            ),

            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Приют зарегистрирован"
                    )
            },
            tags = "shelters"
    )
    @PostMapping("/location_without")
    public ResponseEntity<Void> createShelter(@RequestBody ShelterEntity shelter) {

        logger.debug("\"Post\" createShelter method was invoke...");

        shelterService.saveShelter(shelter);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Изменение информации о приюте с указанным номером (id) в базе данных"
                    + " (схема проезда не изменяется или отсутствует)",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Найден приют",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ShelterEntity.class)
                    )
            ),
            tags = "shelters"
    )
    @PutMapping
    public ResponseEntity<ShelterEntity> updateShelter(@RequestBody ShelterEntity shelter) {

        logger.debug("\"Put\" updateShelter method was invoke...");

        ShelterEntity changedShelter = shelterService.changeShelter(shelter);
        return ResponseEntity.ok(changedShelter);
    }

    @Operation(
            summary = "Добавление/изменение информации в базе данных приюта: адрес (схема проезда)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найден приют",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ShelterEntity.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Приют не найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            },
            tags = "shelters"
    )
    @PutMapping(value = "/location/{shelterId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ShelterEntity> addShelterLocationScheme(@PathVariable Long shelterId
            , @RequestParam MultipartFile shelterLocationSchemeFile) throws IOException {

        logger.debug("\"Put\" addShelterLocationScheme method was invoke...");

        shelterService.saveShelterLocationScheme(shelterId, shelterLocationSchemeFile);
        ShelterEntity shelter = shelterService.findShelterById(shelterId);
        return ResponseEntity.ok(shelter);
    }

    @Operation(
            summary = "Удаление всей информации о приюте с указанным номером (id) в базе данных",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Приют удален",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Long.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Приют не найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            },
            tags = "shelters"
    )
    @DeleteMapping("/{shelterId}")
    public ResponseEntity<Long> deleteShelter(@PathVariable Long shelterId) {

        logger.debug("\"Delete\" deleteShelter method was invoke...");

        shelterService.deleteShelter(shelterId);
        return ResponseEntity.ok(shelterId);
    }

}
