package com.contactApplication.ContactApp.dto;


import lombok.Data;

import java.util.List;

@Data
public class ContactDto {

    private String id;
    private String name;
    private List<String> phoneNumbers;
    private List<String> emails;


}
