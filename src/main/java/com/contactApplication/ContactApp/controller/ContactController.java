package com.contactApplication.ContactApp.controller;



import com.contactApplication.ContactApp.converter.ContactToContactDto;
import com.contactApplication.ContactApp.dto.ContactDto;
import com.contactApplication.ContactApp.dto.ContactListDto;
import com.contactApplication.ContactApp.entity.Contact;
import com.contactApplication.ContactApp.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/contacts")
public class ContactController {

    @Autowired
    private ContactToContactDto contactToContactDto;

    @Autowired
    private ContactServiceImpl contactService;


    @GetMapping
    @ResponseBody
    public ResponseEntity<ContactListDto> getContacts() {

        return ResponseEntity.ok(new ContactListDto(
                contactService.get()
                .stream()
                .map(contactToContactDto::convertToDto)
                .collect(Collectors.toList()))
        );
    }


    @PostMapping
    @ResponseBody
    public ResponseEntity<ContactDto> addContact(@Valid @RequestBody Contact contact) {

        return ResponseEntity.ok(contactToContactDto.convertToDto(contactService.add(contact)));

    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<ContactDto> update(@Valid @RequestBody Contact contact) {
        return ResponseEntity.ok(contactToContactDto.convertToDto(contactService.update(contact)));
    }


    @DeleteMapping
    @ResponseBody
    public void deleteContact(@Valid @RequestBody String uuid) {
        contactService.delete(uuid);
    }


}
