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

import com.baeldung.springsecuritythymeleaf.model.Sef;
import com.baeldung.springsecuritythymeleaf.service.SefService;

@RestController
public class SefController {
	@Autowired
	private SefService sefService;

	@GetMapping(value = "/getCeo")
	public Sef getCeo() {

		return sefService.getCeo();
	}

	@GetMapping(value = "/sef", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Sef> getAllSefi() {
		return this.sefService.getSefi();
	}

	@GetMapping(value = "/sef/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Sef getSef(@PathVariable("id") Long id) {
		return this.sefService.getSef(id);
	}

	@PostMapping(value = "/sef/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Sef createSef(@RequestBody Sef sef) {
		return this.sefService.createSef(sef);
	}

	@PutMapping(value = "/sef/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Sef updateSefa(@RequestBody Sef sef) {
		return this.sefService.updateSef(sef);
	}

	@DeleteMapping(value = "/sef/delete")
	public void deleteSef(@RequestParam("id") Long id) {
		this.sefService.deleteSef(id);

	}
	@PutMapping(value = "/sef/addSefToEchipa")
	public void addAngajatiEchipa(@RequestParam("idSef") Long idSef, @RequestParam("idEchipa") Long idEchipa) {
		sefService.addSefToTeam(idSef, idEchipa);
	}
}
