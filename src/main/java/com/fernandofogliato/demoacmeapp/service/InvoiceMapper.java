package com.fernandofogliato.demoacmeapp.service;

import com.fernandofogliato.demoacmeapp.domain.Invoice;
import com.fernandofogliato.demoacmeapp.dto.InvoiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoiceMapper {
    InvoiceDto toDto(Invoice invoice);
    Invoice toEntity(InvoiceDto invoiceDto);
}
