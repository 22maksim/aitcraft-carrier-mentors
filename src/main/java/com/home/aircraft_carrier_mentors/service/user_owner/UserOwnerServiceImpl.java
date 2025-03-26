package com.home.aircraft_carrier_mentors.service.user_owner;

import com.home.aircraft_carrier_mentors.exception.MyNotFoundException;
import com.home.aircraft_carrier_mentors.mapper.UserOwnerMapper;
import com.home.aircraft_carrier_mentors.model.Contact;
import com.home.aircraft_carrier_mentors.model.Mentor;
import com.home.aircraft_carrier_mentors.model.UserOwner;
import com.home.aircraft_carrier_mentors.model.dto.MentorRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.UserOwnerRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.UserOwnerResponseDto;
import com.home.aircraft_carrier_mentors.repository.MentorRepository;
import com.home.aircraft_carrier_mentors.repository.UserOwnerRepository;
import com.home.aircraft_carrier_mentors.service.contact.ContactService;
import com.home.aircraft_carrier_mentors.service.mentor.MentorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserOwnerServiceImpl implements UserOwnerService {
    private final UserOwnerRepository userOwnerRepository;
    private final UserOwnerMapper userOwnerMapper;
    private final ContactService contactServiceImpl;
    private final MentorService mentorServiceImpl;

    @Override
    @Transactional
    public UserOwnerResponseDto createUserOwner(UserOwnerRequestDto requestDto) {
        UserOwner userOwner = UserOwner.builder().username(requestDto.getUsername()).build();

        userOwner =  userOwnerRepository.save(userOwner);
        return userOwnerMapper.toResponseDto(userOwner);
    }

    @Override
    public UserOwnerResponseDto getUserOwner(Long id) {
        return null;
    }

    @Override
    public void deleteUserOwnerById(Long id) {

    }

    @Override
    public UserOwnerResponseDto becomeMentor(MentorRequestDto mentorRequestDto, Long userOwnerId) {
        List<Contact> contacts = contactServiceImpl.saveAll(mentorRequestDto.getContacts());
        mentorServiceImpl.createMentor(mentorRequestDto);

        UserOwner userOwner = userOwnerRepository.findById(userOwnerId)
                .orElseThrow(() -> new MyNotFoundException("User owner not found."));

        return null;
    }
}
