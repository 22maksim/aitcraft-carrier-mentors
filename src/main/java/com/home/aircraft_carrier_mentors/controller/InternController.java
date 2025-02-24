package com.home.aircraft_carrier_mentors.controller;

import com.home.aircraft_carrier_mentors.model.dto.ContactDto;
import com.home.aircraft_carrier_mentors.model.dto.InternRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.InternResponseDto;
import com.home.aircraft_carrier_mentors.model.dto.MentorRequestDto;
import com.home.aircraft_carrier_mentors.service.intern.InternService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/interns")
@RequiredArgsConstructor
public class InternController {
    private final InternService internServiceImpl;

    @PostMapping
    public InternResponseDto createIntern(@RequestBody @NotNull @Valid InternRequestDto requestDto) {
        return internServiceImpl.createIntern(requestDto);
    }

    @PutMapping("add/mentor/{id}")
    public InternResponseDto addMentor(@NotNull @Valid @RequestBody MentorRequestDto requestDto, @PathVariable Long id) {
        return internServiceImpl.addMentorFromIntern(id, requestDto);
    }

    @PutMapping("add/contact/{id}")
    public InternResponseDto addContact(@NotNull @Valid @RequestBody ContactDto contactDto, @PathVariable Long id) {
        return internServiceImpl.addContact(contactDto, id);
    }

    @GetMapping("{id}")
    public  InternResponseDto getIntern(@NotNull @PathVariable Long id) {
        return internServiceImpl.getInternById(id);
    }
}
