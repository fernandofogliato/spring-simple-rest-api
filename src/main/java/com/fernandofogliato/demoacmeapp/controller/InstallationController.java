package com.fernandofogliato.demoacmeapp.controller;

import com.fernandofogliato.demoacmeapp.domain.Installation;
import com.fernandofogliato.demoacmeapp.dto.InstallationDto;
import com.fernandofogliato.demoacmeapp.exception.ResourceNotFoundException;
import com.fernandofogliato.demoacmeapp.repository.InstallationRepository;
import com.fernandofogliato.demoacmeapp.service.InstallationMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/installetions")
@Api(value = "Acme AP Installation Service")
public class InstallationController {

    private final InstallationRepository installationRepository;
    private final InstallationMapper mapper;

	public InstallationController(InstallationRepository installationRepository,
                                  InstallationMapper mapper) {
		this.installationRepository = installationRepository;
        this.mapper = mapper;
    }

	@ApiOperation("Show all installation")
    @GetMapping
    public List<InstallationDto> getAll() {
		return installationRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    @ApiOperation("Find an installation by code")
    @GetMapping("/{code}")
    public InstallationDto getByCode(@PathVariable String code) {
		Optional<Installation> installation = installationRepository.findByCode(code);
		if (installation.isEmpty()) {
			throw new ResourceNotFoundException();
		}
        return mapper.toDto(installation.get());
    }

    @ApiOperation("Create a new installation")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody InstallationDto installationDto) {
        Installation createdInstallation = installationRepository.save(mapper.toEntity(installationDto));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdInstallation.getId())
				.toUri();
        return ResponseEntity.created(location).build();
    }
}
