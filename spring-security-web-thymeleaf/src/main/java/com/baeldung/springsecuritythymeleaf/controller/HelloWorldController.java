package com.baeldung.springsecuritythymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baeldung.springsecuritythymeleaf.model.Angajat;
import com.baeldung.springsecuritythymeleaf.model.Break;
import com.baeldung.springsecuritythymeleaf.model.Echipa;
import com.baeldung.springsecuritythymeleaf.model.StatusDto;
import com.baeldung.springsecuritythymeleaf.model.enums.Status;
import com.baeldung.springsecuritythymeleaf.repository.AngajatRepository;
import com.baeldung.springsecuritythymeleaf.repository.BreakRepository;
import com.baeldung.springsecuritythymeleaf.repository.UserRepository;
import com.baeldung.springsecuritythymeleaf.service.BreakService;
import com.baeldung.springsecuritythymeleaf.service.EchipaService;

@Controller
public class HelloWorldController {
	@Autowired
	private BreakRepository breakRepository;

	@Autowired
	private BreakService breakService;

	@Autowired
	private UserRepository repo;

	@Autowired
	private AngajatRepository repo1;

	@Autowired
	private EchipaService echipaService;
	
	
	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("message", "Hello World!");

		return "helloworld";
	}

//	@GetMapping("/index")
//	public String index(Model model) {
//		model.addAttribute("message", "Hello World!");
//		List<User> v = repo.findAll();
//		model.addAttribute("users", v);
//		return "index";
//	}

	@GetMapping("/addAngajat")
	public String addAngajat(Model model) {
		List<Angajat> v = repo1.findAll();
		model.addAttribute("angajati", v);
		return "addAngajat";
	}

	@GetMapping("/angajat/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Angajat angajat = repo1.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid angajat Id:" + id));
		model.addAttribute("angajat", angajat);
		return "EditAngajat";
	}

	@PostMapping("/angajat/update/{id}")
	public String updateStudent(@PathVariable("id") long id, @Valid Angajat student, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			student.setId(id);
			return "update-student";
		}

		repo1.save(student);
		model.addAttribute("angajati", repo1.findAll());
		return "addAngajat";
	}

	@GetMapping("/angajat/delete/{id}")
	public String deleteStudent(@PathVariable("id") long id, Model model) {
		Angajat student = repo1.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid angajat Id:" + id));
		repo1.delete(student);
		model.addAttribute("angajati", repo1.findAll());
		return "addAngajat";
	}

	@GetMapping("angajati/signup")
	public String showSignUpForm(Angajat student) {
		return "add-angajat";
	}

	@GetMapping("angajat/list")
	public String showUpdateForm(Model model) {
		model.addAttribute("angajati", repo1.findAll());
		return "addAngajat";
	}

	@PostMapping("angajat/add")
	public String addStudent(@Valid Angajat student, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-angajat";
		}
		repo1.save(student);
		return "redirect:list";
	}

	// to remove

	@GetMapping("/fom")
	public String main(@RequestParam("message") String id, Model model) {
//		model.addAttribute("event", new Event());
		Angajat angajat = repo1.findById(Long.valueOf(id)).get();
		model.addAttribute("angajat", angajat);
		
		String days =breakService.calculateRemainingDays(angajat.getId());
		model.addAttribute("days", days);
		
		return "fom";
	}

	@GetMapping("/login2")
	public String login2(Model model) {
		return "login2";
	}

	@GetMapping("/userpage")
	public String userpage(@RequestParam("id") String id, Model model) {
		Angajat angajat = repo1.findById(Long.valueOf(id)).get();
		model.addAttribute("message", angajat.getAngajatName());
		model.addAttribute("angajat", angajat);
		model.addAttribute("angajati", repo1.findAll());

		List<Break> break1 = breakRepository.getBreaksForAngajat(Long.valueOf(id));
		model.addAttribute("breaks", break1);
		List<Break> vacanteAngajati = breakService.getBreaksForAngajatiFromEchipeSef(Long.valueOf(id));
		model.addAttribute("breaksAngajati", vacanteAngajati);

		Status[] s = Status.class.getEnumConstants();
		List<StatusDto> statusDtos = new ArrayList<StatusDto>();
		for (int i = 0; i < s.length; i++) {

			statusDtos.add(new StatusDto(i, s[i].getVal()));
		}
		model.addAttribute("status", statusDtos);
		
		String days =breakService.calculateRemainingDays(angajat.getId());
		model.addAttribute("days", days);
		return "userpage";
	}
	@PostMapping("/stat")
	public String updateStatus(@RequestParam("id") String id,@RequestParam("breakId") String breakId, @RequestParam("example") String statId, Model model) {
		Angajat angajat = repo1.findById(Long.valueOf(id)).get();
		model.addAttribute("message", angajat.getAngajatName());
		model.addAttribute("angajat", angajat);
		model.addAttribute("angajati", repo1.findAll());
		
		Status[] s = Status.class.getEnumConstants();
		List<StatusDto> statusDtos = new ArrayList<StatusDto>();
		for (int i = 0; i < s.length; i++) {

			statusDtos.add(new StatusDto(i, s[i].getVal()));
		}
		model.addAttribute("status", statusDtos);
		
		
		Break curentBreak = breakService.getBreak(Long.valueOf(breakId));
		breakService.updateBreakStatus(curentBreak, s[Integer.valueOf(statId)]);		
		
		
		
		List<Break> break1 = breakRepository.getBreaksForAngajat(Long.valueOf(id));
		model.addAttribute("breaks", break1);
		List<Break> vacanteAngajati = breakService.getBreaksForAngajatiFromEchipeSef(Long.valueOf(id));
		model.addAttribute("breaksAngajati", vacanteAngajati);

		
		return "userpage";
	}
	
	@GetMapping("/addTeam")
	public String addTeam(@RequestParam("id") String id, Model model) {
		Angajat angajat = repo1.findById(Long.valueOf(id)).get();
		model.addAttribute("message", angajat.getAngajatName());
		model.addAttribute("angajat", angajat);
		model.addAttribute("angajati", repo1.findAll());

		
		
		List<Echipa> myTeams = echipaService.getEchipeGestionateDeAngajat(angajat.getId());
		model.addAttribute("myTeams",myTeams);
		
		
		
		List<Echipa> inTeams = echipaService.getEchipeAngajat(angajat.getId());
		model.addAttribute("inTeams",inTeams);

		return "addTeam";
	}
	
	
		
}