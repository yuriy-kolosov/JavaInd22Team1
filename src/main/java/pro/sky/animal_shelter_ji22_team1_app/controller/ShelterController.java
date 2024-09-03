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

    public final ShelterService shelterService;

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
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Вывод информации о всех приютах из базы данных",
//                    content = @Content(
//                            mediaType = MediaType.APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = ShelterEntity[].class)
//                    )
//            )
//    })
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
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Вывод информации о приюте с указанным номером (id) из базы данных",
//                    content = @Content(
//                            mediaType = MediaType.APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = ShelterEntity.class)
//                    )
//            )
//    })
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
                            description = "Адрес приюта (схема проезда) не найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            },
            tags = "shelters"
    )
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Вывод информации о приюте из базы данных: адрес (схема проезда)"
//            ),
//            @ApiResponse(
//                    responseCode = "400",
//                    description = "Адрес приюта (схема проезда) в базе данных отсутствует"
//            )
//    })
    @GetMapping("/scheme{shelterId}")
    public ResponseEntity<byte[]> getShelterLocationScheme(@PathVariable Long shelterId) {

        logger.debug("\"Get\" getShelterLocationScheme method was invoke...");

        ShelterEntity shelter = shelterService.findShelterById(shelterId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(shelter.getMediaType()));
        headers.setContentLength(shelter.getLocationSchemeData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(shelter.getLocationSchemeData());
    }

    @Operation(
            summary = "Запись информации о новом приюте в базу данных (без схемы проезда)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Приют зарегистрирован",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ShelterEntity.class)
                            )
                    )
            },
            tags = "shelters"
    )
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Запись информации о новом приюте в базу данных (без схемы проезда)"
//            )
//    })
    @PostMapping
    public ResponseEntity<ShelterEntity> createShelter(@RequestBody ShelterEntity shelter) {

        logger.debug("\"Post\" createShelter method was invoke...");

        ShelterEntity postedShelter = shelterService.saveShelter(shelter);
        return ResponseEntity.ok(postedShelter);
    }

    @Operation(
            summary = "Изменение информации о приюте с указанным номером (id) в базе данных (без схемы проезда)",
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
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Изменение информации о приюте с указанным номером (id) в базе данных (без схемы проезда)",
//                    content = @Content(
//                            mediaType = MediaType.APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = ShelterEntity.class)
//                    )
//            )
//    })
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
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Добавление/изменение информации в базе данных приюта: адрес (схема проезда)"
//            )
//    })
    @PutMapping(value = "/location{shelterId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
//    @ApiResponses({
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "Удаление всей информации о приюте с указанным номером (id) в базе данных"
//            )
//    })
    @DeleteMapping("/{shelterId}")
    public ResponseEntity<Long> deleteShelter(@PathVariable Long shelterId) {

        logger.debug("\"Delete\" deleteShelter method was invoke...");

        shelterService.deleteShelter(shelterId);
        return ResponseEntity.ok(shelterId);
    }

}
