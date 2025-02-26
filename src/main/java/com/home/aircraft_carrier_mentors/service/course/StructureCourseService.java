package com.home.aircraft_carrier_mentors.service.course;

import com.home.aircraft_carrier_mentors.model.dto.StageCourseRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.StructureCourseResponseDto;
import jakarta.validation.constraints.NotNull;

public interface StructureCourseService {
    StructureCourseResponseDto addStageFromStructureById(Long id, StageCourseRequestDto stageStructureDto);

    StructureCourseResponseDto deleteStageFromStructureByStructureIdAndStageId(@NotNull Long structureId, @NotNull Long stageId);
}
