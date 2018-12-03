package com.contactApplication.ContactApp.service;


import com.contactApplication.ContactApp.entity.Contact;


import java.util.List;


public interface ContactService

{
    List<Contact> get();

    Contact add(Contact contact);

    Contact update(Contact contact);

    void delete(String uuid);


}
