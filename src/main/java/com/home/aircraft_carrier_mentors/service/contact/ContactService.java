package com.home.aircraft_carrier_mentors.service.contact;

import com.home.aircraft_carrier_mentors.model.Contact;
import com.home.aircraft_carrier_mentors.model.dto.ContactDto;

import java.util.List;

public interface ContactService {

    List<Contact> saveAll(List<ContactDto> contactDtoList);

    List<ContactDto> getContactDtoList(List<Contact> contacts);

    List<Contact> getContactList(List<Long> contactDtoList);
}
