package com.fernandofogliato.demoacmeapp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class AddressDto {

    private long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
