package com.fernandofogliato.demoacmeapp.service;

import com.fernandofogliato.demoacmeapp.domain.Address;
import com.fernandofogliato.demoacmeapp.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {
    AddressDto toDto(Address address);
    Address toEntity(AddressDto addressDto);
}
