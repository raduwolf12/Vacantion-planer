package com.baeldung.springsecuritythymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.baeldung.springsecuritythymeleaf.repository.AngajatRepository;
import com.baeldung.springsecuritythymeleaf.service.AngajatService;

@Controller
public class MainController {

	@Autowired
	private AngajatRepository angajatRepository;

//	@GetMapping("/")
	public String index(Model model) {
		return "login";
	}

	@RequestMapping({ "/index", "/" })
	public RedirectView login(@RequestParam("username") String name, @RequestParam("password") String password,
			Model model) {

		Long id = angajatRepository.getAngajatByEmail(name).getId();
		return new RedirectView("/userpage?id=" + id);

	}

}
