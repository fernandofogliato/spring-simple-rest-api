package com.fernandofogliato.demoacmeapp.controller;

import com.fernandofogliato.demoacmeapp.domain.Installation;
import com.fernandofogliato.demoacmeapp.exception.ResourceNotFoundException;
import com.fernandofogliato.demoacmeapp.repository.InstallationRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/installetions")
@Api(value = "Acme AP Installation Service")
public class InstallationController {

    private final InstallationRepository installationRepository;

	public InstallationController(InstallationRepository installationRepository) {
		this.installationRepository = installationRepository;
	}

	@ApiOperation("Show all installation")
    @GetMapping
    public List<Installation> getAllInstalacoes() {
		return installationRepository.findAll();
    }


    @ApiOperation("Find an installation by code")
    @GetMapping("/{code}")
    public Optional<Installation> getByCode(@PathVariable String code) {
		Optional<Installation> installation = installationRepository.findByCode(code);
		if (installation.isEmpty()) {
			throw new ResourceNotFoundException();
		}
        return installation;
    }

    @ApiOperation("Create a new installation")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Installation installation) {
        Installation createdInstallation = installationRepository.save(installation);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdInstallation.getId())
				.toUri();
        return ResponseEntity.created(location).build();
    }
}
