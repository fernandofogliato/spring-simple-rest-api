package com.fernandofogliato.demoacmeapp.service;

import com.fernandofogliato.demoacmeapp.domain.Customer;
import com.fernandofogliato.demoacmeapp.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customerDto);
}
