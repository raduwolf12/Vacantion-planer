package com.baeldung.springsecuritythymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.baeldung.springsecuritythymeleaf.dto.AngajatDto;
import com.baeldung.springsecuritythymeleaf.model.Angajat;
import com.baeldung.springsecuritythymeleaf.service.AngajatService;

@Controller
public class AngajatController {
	@Autowired
	private AngajatService angajatService;

	@GetMapping(value = "/angajati", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AngajatDto> getAngajats() {
		return this.angajatService.getAngajati();
	}

	@GetMapping(value = "/angajat/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AngajatDto getAngajat(@PathVariable("id") Long id) {
		return this.angajatService.getAngajat(id);
	}

	@PostMapping(value = "/angajat/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Angajat createAngajat(@RequestBody Angajat angajat) {
		return this.angajatService.createAngajat(angajat);
	}

	@PutMapping(value = "/angajat/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Angajat updateAngajat(@RequestBody Angajat angajat) {
		return this.angajatService.updateAngajat(angajat);
	}

	@DeleteMapping(value = "/angajat/delete")
	public void deleteAngajat(@RequestParam("id") String id) {
		this.angajatService.deleteAngajat(Long.valueOf(id));

	}

	@GetMapping(value = "/getAngajatiFromEchipeSef/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AngajatDto> getAngajatiFromEchipeSef(@PathVariable("id") Long id) {
		return this.angajatService.getAngajatiEchipe(id);
	}
	
//	@GetMapping(value = "/getBreaksForAngajatiFromEchipeSef/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public String getBreaksForAngajatiFromEchipeSef(@PathVariable("id") Long id) {
//		
//		return this.angajatService.getAngajatiEchipe(id);
//	}
		

}
