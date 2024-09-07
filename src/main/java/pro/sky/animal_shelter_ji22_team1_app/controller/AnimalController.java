package pro.sky.animal_shelter_ji22_team1_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.Collection;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.animal_shelter_ji22_team1_app.entity.Animal;
import pro.sky.animal_shelter_ji22_team1_app.exception.ErrorDto;
import pro.sky.animal_shelter_ji22_team1_app.service.AnimalService;
import pro.sky.animal_shelter_ji22_team1_app.service.AnimalServiceImpl;

@RestController
@RequestMapping("animals")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalServiceImpl animalService) {
        this.animalService = animalService;
    }

    @Operation(
            summary = "Вывод всех животных приюта",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные животные",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Animal.class))
                            )
                    )
            },
            tags = "animals"
    )
    @GetMapping
    public ResponseEntity<Collection<Animal>> getAllAnimals() {
        Collection<Animal> animals = animalService.findAll();
        return ResponseEntity.ok(animals);
    }

    @Operation(
            summary = "Вывод животного приюта по идентификатору",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденное животное",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Animal.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Животное не найдено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            },
            tags = "animals"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable Long id) {
        Animal animal = animalService.findById(id);
        return ResponseEntity.ok(animal);
    }

    @Operation(
            summary = "Создание нового животного",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Создаваемое животное",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Animal.class)
                    )
            ),
            tags = "animals"
    )
    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalService.save(animal);
    }

    @Operation(
            summary = "Изменение животного по идентификатору",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Редактируемое животное",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Animal.class)
                    )
            ),
            tags = "animals"
    )
    @PutMapping()
    public ResponseEntity<Animal> changeAnimal(@RequestBody Animal animal) {
        Animal
                changedAnimal = animalService.change(animal);
        return ResponseEntity.ok(changedAnimal);
    }

    @Operation(
            summary = "Удаление животного",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Животное удалено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Long.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Животное не найдено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            },
            tags = "animals"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAnimal(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.ok(id);
    }
}
