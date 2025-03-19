package com.home.aircraft_carrier_mentors.controller;

import com.home.aircraft_carrier_mentors.model.dto.StageCourseRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.StructureCourseResponseDto;
import com.home.aircraft_carrier_mentors.service.course.StructureCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/structure-course")
@RequiredArgsConstructor
@Tag(name = "Structure API", description = "Методы для работы со структурами курсов")
public class StructureCourseController {
    private final StructureCourseService structureCourseServiceImpl;

    @PutMapping("/{id}")
    public StructureCourseResponseDto addStageFromStructureById(@PathVariable Long id,
            @RequestBody @NotNull @Valid StageCourseRequestDto stageStructureDto) {
        return structureCourseServiceImpl.addStageFromStructureById(id, stageStructureDto);
    }

    @Operation(summary = "Удалить этап из структуры", description = "Удаляет этап по его ID в структуре")
    @DeleteMapping("/{structureId}/stage/{stageId}")
    @CachePut(value = "course-stages", key = "#result.courseId")
    public StructureCourseResponseDto deleteStageFromStructureByStructureIdAndStageId(
            @NotNull @PathVariable(name = "structureId") Long structureId,
            @PathVariable(name = "stageId") @NotNull Long stageId) {
        return structureCourseServiceImpl.deleteStageFromStructureByStructureIdAndStageId(structureId, stageId);
    }

    @GetMapping("get-all-by-course-id/{id}")
    @Cacheable(value = "course-stages", key = "#idCourse")
    public List<StructureCourseResponseDto> getAllStagesFromStructureCourseByCourseId(
            @NotNull @PathVariable(name = "id") Long idCourse) {
        return structureCourseServiceImpl.getAllStagesFromStructureCourseByCourseId(idCourse);
    }
}
