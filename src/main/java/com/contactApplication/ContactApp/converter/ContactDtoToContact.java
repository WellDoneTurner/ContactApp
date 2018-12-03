package com.contactApplication.ContactApp.converter;

import com.contactApplication.ContactApp.dto.ContactDto;
import com.contactApplication.ContactApp.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactDtoToContact {


    public Contact convertToContact(ContactDto contactDto)
    {
        Contact contact = new Contact();
        contact.setUuid(contactDto.getId());
        contact.setName(contactDto.getName());
        contact.setEmails(contactDto.getEmails());
        contact.setPhoneNumbers(contactDto.getPhoneNumbers());
        return contact;

    }
}
