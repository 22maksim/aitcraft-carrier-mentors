package com.home.aircraft_carrier_mentors.service.course;

import com.home.aircraft_carrier_mentors.model.dto.StageCourseDto;
import com.home.aircraft_carrier_mentors.model.dto.StructureCourseResponseDto;
import com.home.aircraft_carrier_mentors.repository.StageCourseRepository;
import com.home.aircraft_carrier_mentors.repository.StructureCourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StructureCourseServiceImpl implements StructureCourseService {
    private final StageCourseRepository stageCourseRepository;
    private final StructureCourseRepository structureCourseRepository;

    @Transactional
    @Override
    public StructureCourseResponseDto addStageFromStructureById(Long id, StageCourseDto stageStructureDto) {
        
        return null;
    }
}
