package com.home.aircraft_carrier_mentors.service.course;

import com.home.aircraft_carrier_mentors.model.dto.StageCourseDto;
import com.home.aircraft_carrier_mentors.model.dto.StructureCourseResponseDto;

public interface StructureCourseService {
    StructureCourseResponseDto addStageFromStructureById(Long id, StageCourseDto stageStructureDto);
}
