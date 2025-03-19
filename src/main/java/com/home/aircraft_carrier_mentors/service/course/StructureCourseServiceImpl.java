package com.home.aircraft_carrier_mentors.service.course;

import com.home.aircraft_carrier_mentors.exception.MyNotFoundException;
import com.home.aircraft_carrier_mentors.mapper.custom.CustomStructureCourseMapper;
import com.home.aircraft_carrier_mentors.model.StageCourse;
import com.home.aircraft_carrier_mentors.model.StructureCourse;
import com.home.aircraft_carrier_mentors.model.dto.StageCourseRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.StructureCourseResponseDto;
import com.home.aircraft_carrier_mentors.repository.StageCourseRepository;
import com.home.aircraft_carrier_mentors.repository.StructureCourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StructureCourseServiceImpl implements StructureCourseService {
    private final StageCourseRepository stageCourseRepository;
    private final StructureCourseRepository structureCourseRepository;
    private final CustomStructureCourseMapper customStructureCourseMapper;

    @Transactional
    @Override
    public StructureCourseResponseDto addStageFromStructureById(Long id, StageCourseRequestDto stageStructureDto) {
        StageCourse stageCourse = new StageCourse();
        stageCourse.setTitle(stageStructureDto.getTitle());
        stageCourse.setDescription(stageStructureDto.getDescription());

        StructureCourse structureCourse = structureCourseRepository.findById(id)
                .orElseThrow(() -> new MyNotFoundException("Structure course not found. Id: " + id));

        structureCourse.getStages().add(stageCourse);
        stageCourse.setStructureCourse(structureCourse);
        structureCourseRepository.save(structureCourse);
        stageCourseRepository.save(stageCourse);

        return customStructureCourseMapper.toResponseDto(structureCourse);
    }

    @Transactional
    @Override
    public StructureCourseResponseDto getStructureCourseByCourseId(Long idCourse) {
        StructureCourse structure = structureCourseRepository.findByCourseId(idCourse);
        return customStructureCourseMapper.toResponseDto(structure);
    }

    @Transactional
    @Override
    public StructureCourseResponseDto deleteStageFromStructureByStructureIdAndStageId(Long structureId, Long stageId) {
        StructureCourse structureCourse = structureCourseRepository.findById(structureId)
                .orElseThrow(() -> new MyNotFoundException("Structure course not found. Id: " + structureId));
        StageCourse stageCourse = stageCourseRepository.findById(stageId)
                .orElseThrow(() -> new MyNotFoundException("Stage course not found. Id: " + stageId));

        if (!structureCourse.getStages().remove(stageCourse)) {
            throw new MyNotFoundException("Stage course not found. Id: " + stageId);
        }

        structureCourseRepository.save(structureCourse);

        return customStructureCourseMapper.toResponseDto(structureCourse);
    }
}
