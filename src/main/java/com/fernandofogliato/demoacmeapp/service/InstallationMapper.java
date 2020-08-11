package com.fernandofogliato.demoacmeapp.service;

import com.fernandofogliato.demoacmeapp.domain.Installation;
import com.fernandofogliato.demoacmeapp.dto.InstallationDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InstallationMapper {
    InstallationDto toDto(Installation installation);
    Installation toEntity(InstallationDto installationDto);
}
