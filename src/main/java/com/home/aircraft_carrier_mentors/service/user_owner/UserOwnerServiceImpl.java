package com.home.aircraft_carrier_mentors.service.user_owner;

import com.home.aircraft_carrier_mentors.exception.MyNotFoundException;
import com.home.aircraft_carrier_mentors.mapper.UserOwnerMapper;
import com.home.aircraft_carrier_mentors.model.Mentor;
import com.home.aircraft_carrier_mentors.model.UserOwner;
import com.home.aircraft_carrier_mentors.model.dto.UserOwnerRequestDto;
import com.home.aircraft_carrier_mentors.model.dto.UserOwnerResponseDto;
import com.home.aircraft_carrier_mentors.repository.MentorRepository;
import com.home.aircraft_carrier_mentors.repository.UserOwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserOwnerServiceImpl implements UserOwnerService {
    private final UserOwnerRepository userOwnerRepository;
    private final MentorRepository mentorRepository;
    private final UserOwnerMapper userOwnerMapper;

    @Override
    @Transactional
    public UserOwnerResponseDto createUserOwner(UserOwnerRequestDto requestDto) {
        UserOwner userOwner = UserOwner.builder().username(requestDto.getUsername()).build();
        if (requestDto.getMentorId() != null) {
            Mentor mentor = mentorRepository.findById(requestDto.getMentorId())
                    .orElseThrow(() -> new MyNotFoundException("Mentor id not found."));
            userOwner.setMentor(mentor);
        }
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
}
