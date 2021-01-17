package com.baeldung.springsecuritythymeleaf.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.baeldung.springsecuritythymeleaf.model.Angajat;
import com.baeldung.springsecuritythymeleaf.model.Break;
import com.baeldung.springsecuritythymeleaf.model.enums.Status;
import com.baeldung.springsecuritythymeleaf.repository.AngajatRepository;
import com.baeldung.springsecuritythymeleaf.repository.BreakRepository;
import com.baeldung.springsecuritythymeleaf.service.BreakService;

@RestController
public class BreakController {
	@Autowired
	private BreakRepository breakRepository;
	@Autowired
	private AngajatRepository angajatRepository;
	@Autowired
	private BreakService breakService;

	@GetMapping("/break/addBreak")
	public RedirectView addBreak(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("id") Long id, Model model) throws ParseException {
		Angajat angajat = angajatRepository.findById(id).get();
		List<Angajat> angajati = new ArrayList<Angajat>();
		angajati.add(angajat);

		int days =Integer.valueOf(breakService.calculateRemainingDays(angajat.getId()));
		
		if(days==0)
		{
			return  new RedirectView("/fom?message="+id);
		}
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = myFormat.parse(startDate);
		Date date2 = myFormat.parse(endDate);
		if(date1.after(date2))
		{
			return  new RedirectView("/fom?message="+id);
		}
		if(date2.before(date1))
		{
			return  new RedirectView("/fom?message="+id);
		}
		
		int days2 =breakService.getWorkingDaysBetweenTwoDates(date1,date2)+1;
		if(days-days2<0)
		{
			return  new RedirectView("/fom?message="+id);
		}
		
		Break break1 = new Break(startDate, endDate, Status.PENDING, angajati);
		breakRepository.save(break1);


		
//		List<Angajat> v = repo1.findAll();
//		model.addAttribute("angajati", v);
		return  new RedirectView("/fom?message="+id);
	}

	@GetMapping("/break/getBreak")
	public void getBreak(@RequestParam("id") Long id, Model model) {

		List<Break> break1 = breakRepository.getBreaksForAngajat(id);

//		List<Angajat> v = repo1.findAll();
		model.addAttribute("breaks", break1);
//		return break1;
//		return "addAngajat";
	}

	@GetMapping("/break/delete")
	public RedirectView removeBreak(@RequestParam("id") Long id, @RequestParam("id2") Long idAngajat, Model model) {

		try {
			breakRepository.deleteAngajatBreak(id);

		} catch (Exception e) {
			System.out.println("ceva a mers nsp");
		}
		breakRepository.deleteById(id);
		return new RedirectView("/userpage?id=" + idAngajat);

	}

	@GetMapping("/statusuri")
	public String getStatus(Model model) {

		Status[] statusuri = Status.class.getEnumConstants();

		List<String> v = new ArrayList<String>();
		for (Status s : statusuri) {
			v.add(s.getVal());
		}
		model.addAttribute("status", v);
		return "addAngajat";
	}

}
