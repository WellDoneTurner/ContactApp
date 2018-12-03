package com.contactApplication.ContactApp.service.impl;

import com.contactApplication.ContactApp.entity.Contact;
import com.contactApplication.ContactApp.entity.User;
import com.contactApplication.ContactApp.repository.ContactRepository;
import com.contactApplication.ContactApp.repository.UserRepository;
import com.contactApplication.ContactApp.service.ContactService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    public static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static final String PHONE_PATTERN = ("^((\\+?380)([0-9]{9}))$");

    private ContactRepository contactRepository;
    private UserRepository userRepository;

    public ContactServiceImpl(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Contact> get() {
        User user = getAuthorizedUser();
        return contactRepository.findAllByUser_Uuid(user.getUuid());
    }

    @Override
    public Contact add(Contact contact) {
        validateNumbers(contact.getPhoneNumbers());
        validateEmails(contact.getEmails());

        contact.setUser(getAuthorizedUser());

        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        validateNumbers(contact.getPhoneNumbers());
        validateEmails(contact.getEmails());

        return contactRepository.findById(contact.getUuid())
            .map(oldContact -> {
                oldContact.setName(contact.getName());
                oldContact.setEmails(contact.getEmails());
                oldContact.setPhoneNumbers(contact.getPhoneNumbers());

                return contactRepository.save(oldContact);
            })
        .orElseThrow(() -> new EntityNotFoundException(String.format("There is no contact with id: %s", contact.getUuid())));
    }

    private void validateNumbers(List<String> numbers) {
        numbers.forEach(phoneNumber -> {
            if (!phoneNumber.matches(PHONE_PATTERN)) {
                throw new RuntimeException(String.format("Number %s is not correct", phoneNumber));
            }
        });
    }

    private void validateEmails(List<String> emails) {
        emails.forEach(email -> {
            if (!email.matches(EMAIL_PATTERN)) {
                throw new RuntimeException(String.format("Email %s is not correct", email));
            }
        });
    }

    @Override
    public void delete(String uuid) {


        contactRepository.deleteByUuidAndUser_Uuid(uuid,getAuthorizedUser().getUuid());
    }

    private User getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
