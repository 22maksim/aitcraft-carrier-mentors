package com.home.aircraft_carrier_mentors.service.contact;

import com.home.aircraft_carrier_mentors.mapper.ContactMapper;
import com.home.aircraft_carrier_mentors.model.Contact;
import com.home.aircraft_carrier_mentors.model.dto.ContactDto;
import com.home.aircraft_carrier_mentors.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Transactional
    @Override
    public List<Contact> saveAll(List<ContactDto> contactDtoList) {
        List<Contact> contacts = contactDtoList.stream()
                .map(contactMapper::mapDtoToContact)
                .toList();
        return contactRepository.saveAll(contacts);
    }

    @Override
    public List<ContactDto> getContactDtoList(List<Contact> contacts) {
        return List.of();
    }

    @Override
    public List<Contact> getContactList(List<Long> contactDtoList) {
        return List.of();
    }
}
