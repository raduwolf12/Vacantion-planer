package com.baeldung.springsecuritythymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.springsecuritythymeleaf.model.Echipa;
import com.baeldung.springsecuritythymeleaf.service.EchipaService;

@RestController
public class EchipaController {
	@Autowired
	private EchipaService echipaService;

	@GetMapping(value = "/echipe", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Echipa> getAllEchipe() {
		return this.echipaService.getEchipa();
	}

	@GetMapping(value = "/echipa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Echipa getEchipa(@PathVariable("id") Long id) {
		return this.echipaService.getEchipa(id);
	}

	@PostMapping(value = "/echipa/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Echipa createEchipa(@RequestBody Echipa echipa) {
		return this.echipaService.createEchipa(echipa);
	}

	@PutMapping(value = "/echipa/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Echipa updateEchipa(@RequestBody Echipa echipa) {
		return this.echipaService.updateEchipa(echipa);
	}

	@DeleteMapping(value = "/echipa/delete")
	public void deleteEchipa(@RequestParam("id") Long id) {
		this.echipaService.deleteEchipa(id);

	}

	@PutMapping(value = "/echipa/addAngajati")
	public void addAngajatiEchipa(@RequestParam("idAngajat") Long idAngajat, @RequestParam("idEchipa") Long idEchipa) {
		echipaService.addAngajatToEchipa(idAngajat, idEchipa);
	}

}
