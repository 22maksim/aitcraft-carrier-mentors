package com.home.aircraft_carrier_mentors.service.mentor;

import com.home.aircraft_carrier_mentors.mapper.ContactMapper;
import com.home.aircraft_carrier_mentors.mapper.InternMapper;
import com.home.aircraft_carrier_mentors.mapper.MentorMapper;
import com.home.aircraft_carrier_mentors.model.Contact;
import com.home.aircraft_carrier_mentors.model.Mentor;
import com.home.aircraft_carrier_mentors.model.dto.MentorRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.MentorResponseDto;
import com.home.aircraft_carrier_mentors.repository.ContactRepository;
import com.home.aircraft_carrier_mentors.repository.InternRepository;
import com.home.aircraft_carrier_mentors.repository.MentorRepository;
import com.home.aircraft_carrier_mentors.service.contact.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {
    private final ContactService contactService;
    private final MentorRepository mentorRepository;
    private final InternRepository internRepository;
    private final ContactRepository contactRepository;
    private final MentorMapper mentorMapper;
    private final InternMapper internMapper;
    private final ContactMapper contactMapper;

    @Override
    public MentorResponseDto createMentor(MentorRequestDto requestDto) {
        List<Contact> contacts = contactService.saveAll(requestDto.getContacts());
        Mentor mentor = mentorMapper.toMentor(requestDto);
        mentor.setContacts(contacts);
        System.out.println(mentor.getId() + " " + mentor.getName() + " " + mentor.getContacts());
        mentorRepository.save(mentor);

        return mentorMapper.toMentorResponseDto(mentor);
    }

}
