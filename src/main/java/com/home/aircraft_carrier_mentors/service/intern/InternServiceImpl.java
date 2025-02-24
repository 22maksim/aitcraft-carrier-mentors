package com.home.aircraft_carrier_mentors.service.intern;

import com.home.aircraft_carrier_mentors.exception.MyNotFoundException;
import com.home.aircraft_carrier_mentors.mapper.ContactMapper;
import com.home.aircraft_carrier_mentors.mapper.InternMapper;
import com.home.aircraft_carrier_mentors.model.Contact;
import com.home.aircraft_carrier_mentors.model.Intern;
import com.home.aircraft_carrier_mentors.model.Mentor;
import com.home.aircraft_carrier_mentors.model.dto.ContactDto;
import com.home.aircraft_carrier_mentors.model.dto.InternRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.InternResponseDto;
import com.home.aircraft_carrier_mentors.model.dto.MentorRequestDto;
import com.home.aircraft_carrier_mentors.repository.ContactRepository;
import com.home.aircraft_carrier_mentors.repository.InternRepository;
import com.home.aircraft_carrier_mentors.repository.MentorRepository;
import com.home.aircraft_carrier_mentors.service.contact.ContactService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
@Service
@RequiredArgsConstructor
public class InternServiceImpl implements InternService {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    private final InternRepository internRepository;
    private final MentorRepository mentorRepository;
    private final ContactRepository contactRepository;
    private final ContactService contactService;
    private final InternMapper internMapper;
    private final ContactMapper contactMapper;

    @Transactional
    @Override
    public InternResponseDto createIntern(InternRequestDto requestDto) {
        System.out.println(requestDto.getContacts());
        List<Contact> contacts = contactService.saveAll(requestDto.getContacts());

        Intern intern = Intern.builder().name(requestDto.getName()).contacts(contacts).createdAt(Instant.now()).build();

        System.out.println(intern.getName());
        intern = internRepository.save(intern);
        LOGGER.info("Creating Intern. Name: {}" + intern.getName() + ". Time: " + intern.getCreatedAt());
        return internMapper.internToResponseDto(intern);
    }

    @Transactional
    @Override
    public InternResponseDto addMentorFromIntern(Long id, MentorRequestDto requestDto) {
        Intern intern = internRepository.findById(id)
                .orElseThrow(() -> new MyNotFoundException("Intern not found"));

        Mentor mentor = mentorRepository.findById(requestDto.getId())
                .orElseThrow(() -> new MyNotFoundException("Mentor not found"));

        intern.getMentors().add(mentor);
        mentor.getInterns().add(intern);

        return internMapper.internToResponseDto(intern);
    }

    @Transactional
    @Override
    public InternResponseDto addContact(ContactDto contactDto, Long id) {
        Contact contact = contactService.saveAll(List.of(contactDto)).get(0);

        Intern intern = internRepository.findById(id)
                .orElseThrow(() -> new MyNotFoundException("Intern not found"));

        intern.getContacts().add(contact);

        return internMapper.internToResponseDto(intern);
    }

    @Transactional
    @Override
    public InternResponseDto getInternById(Long id) {
        Intern intern = internRepository.findById(id)
                .orElseThrow(() -> new MyNotFoundException("Intern not found"));
        return internMapper.internToResponseDto(intern);
    }
}
