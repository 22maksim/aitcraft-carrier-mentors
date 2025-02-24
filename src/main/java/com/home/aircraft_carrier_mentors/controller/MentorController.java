package com.home.aircraft_carrier_mentors.controller;

import com.home.aircraft_carrier_mentors.model.dto.MentorRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.MentorResponseDto;
import com.home.aircraft_carrier_mentors.service.mentor.MentorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/mentors")
@RequiredArgsConstructor
public class MentorController {
    private final MentorService mentorServiceImpl;

    @PostMapping
    public MentorResponseDto createMentor(@RequestBody @NotNull @Valid MentorRequestDto requestDto) {
        return mentorServiceImpl.createMentor(requestDto);
    }

    @GetMapping("/{id}")
    public MentorResponseDto getMentorById(@PathVariable @NotNull Long id) {
        return mentorServiceImpl.findMentorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMentorById(@PathVariable @NotNull Long id) {
        mentorServiceImpl.deleteMentorById(id);
    }
}
