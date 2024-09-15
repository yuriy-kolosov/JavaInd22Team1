package pro.sky.animal_shelter_ji22_team1_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.Collection;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.animal_shelter_ji22_team1_app.entity.ReportEntity;
import pro.sky.animal_shelter_ji22_team1_app.entity.UserEntity;
import pro.sky.animal_shelter_ji22_team1_app.exception.ErrorDto;
import pro.sky.animal_shelter_ji22_team1_app.service.ReportService;

@RestController
@RequestMapping("reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Operation(
            summary = "Вывод всех отчётов",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные отчёты",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ReportEntity.class))
                            )
                    )
            },
            tags = "reports"
    )
    @GetMapping
    public ResponseEntity<Collection<ReportEntity>> getAll() {
        Collection<ReportEntity> reports = reportService.findAll();
        return ResponseEntity.ok(reports);
    }

    @Operation(
            summary = "Вывод отчёта по идентификатору",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденный отчёт",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ReportEntity.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Отчёт не найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)
                            )
                    )
            },
            tags = "reports"
    )
    @GetMapping("/{reportId}")
    public ResponseEntity<ReportEntity> getById(@PathVariable Long reportId) {
        ReportEntity report = reportService.findById(reportId);
        return ResponseEntity.ok(report);
    }

    @Operation(
            summary = "Изменение отчёта по идентификатору",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Редактируемый отчёт",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReportEntity.class)
                    )
            ),
            tags = "reports"
    )
    @PutMapping()
    public ResponseEntity<ReportEntity> change(@RequestBody ReportEntity report) {
        ReportEntity changed = reportService.change(report);
        return ResponseEntity.ok(changed);
    }

    @Operation(
            summary = "Принятие отчёта по идентификатору",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Принятый отчёт",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReportEntity.class)
                    )
            ),
            tags = "reports"
    )
    @PutMapping("/id")
    public ResponseEntity<ReportEntity> acceptance(@RequestParam Long id) {
        ReportEntity report = reportService.findById(id);
        report.setAccepted(true);
        return ResponseEntity.ok(reportService.change(report));
    }
}
