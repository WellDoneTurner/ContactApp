package com.contactApplication.ContactApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "contacts")
public class Contact extends BaseEntity {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid",
            foreignKey = @ForeignKey(name = "fk_user_to_contact"))
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "emails")
    @ElementCollection
    private List<String> emails;

    @Column(name = "phoneNumbers")
    @ElementCollection
    private List<String> phoneNumbers;
}
