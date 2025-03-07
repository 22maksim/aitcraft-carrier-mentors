package com.home.aircraft_carrier_mentors.controller;

import com.home.aircraft_carrier_mentors.model.dto.StageCourseRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.StructureCourseResponseDto;
import com.home.aircraft_carrier_mentors.service.course.StructureCourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/structure-course")
@RequiredArgsConstructor
public class StructureCourseController {
    private final StructureCourseService structureCourseServiceImpl;

    @PutMapping("/{id}")
    public StructureCourseResponseDto addStageFromStructureById(@PathVariable Long id,
            @RequestBody @NotNull @Valid StageCourseRequestDto stageStructureDto) {
        return structureCourseServiceImpl.addStageFromStructureById(id, stageStructureDto);
    }

    @DeleteMapping("/{structureId}/stage/{stageId}")
    public StructureCourseResponseDto deleteStageFromStructureByStructureIdAndStageId(
            @NotNull @PathVariable(name = "structureId") Long structureId,
            @PathVariable(name = "stageId") @NotNull Long stageId) {
        return structureCourseServiceImpl.deleteStageFromStructureByStructureIdAndStageId(structureId, stageId);
    }
}
