package com.contactApplication.ContactApp.converter;



import com.contactApplication.ContactApp.dto.ContactDto;
import com.contactApplication.ContactApp.entity.Contact;
import org.springframework.stereotype.Component;



@Component
public class ContactToContactDto {


    public ContactDto convertToDto(Contact contact)
    {
        ContactDto dto = new ContactDto();
        dto.setId(contact.getUuid());
        dto.setName(contact.getName());
        dto.setEmails(contact.getEmails());
        dto.setPhoneNumbers(contact.getPhoneNumbers());
        return dto;


    }






}
