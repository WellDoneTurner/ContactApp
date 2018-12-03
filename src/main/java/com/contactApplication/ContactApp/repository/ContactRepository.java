package com.contactApplication.ContactApp.repository;

import com.contactApplication.ContactApp.entity.Contact;
import com.contactApplication.ContactApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
   Optional<Contact> findByUuidAndUser(String uuid, User user);
   List<Contact> findAllByUser_Uuid(String uuid);
   void deleteByUuidAndUser_Uuid(String uuid, String userUuid);

}
