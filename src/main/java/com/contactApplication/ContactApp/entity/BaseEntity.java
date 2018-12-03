package com.contactApplication.ContactApp.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String uuid;

    @PrePersist
    protected void prePersist() {
        if (getUuid() == null || getUuid().isEmpty()) {
            uuid = UUID.randomUUID().toString();
        }
    }
}
