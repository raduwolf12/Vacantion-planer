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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.baeldung.springsecuritythymeleaf.dto.AngajatDto;
import com.baeldung.springsecuritythymeleaf.model.Angajat;
import com.baeldung.springsecuritythymeleaf.model.Break;
import com.baeldung.springsecuritythymeleaf.model.enums.Status;
import com.baeldung.springsecuritythymeleaf.repository.AngajatRepository;
import com.baeldung.springsecuritythymeleaf.repository.BreakRepository;
import com.baeldung.springsecuritythymeleaf.service.AngajatService;
import com.baeldung.springsecuritythymeleaf.service.BreakService;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;

@RestController
public class BreakController {
//	@Autowired
//	private BreakRepository breakRepository;
//	
//	@Autowired
//	private AngajatRepository angajatRepository;

	@Autowired
	private BreakService breakService;

	@Autowired
	private AngajatService angajatService;

	@GetMapping("/break/addBreak")
	public RedirectView addBreak(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("id") Long id, Model model, RedirectAttributes redirectAttributes) throws ParseException {
		AngajatDto angajat = angajatService.getAngajat(id);
		List<AngajatDto> angajati = new ArrayList<AngajatDto>();
		angajati.add(angajat);

		int days = Integer.valueOf(breakService.calculateRemainingDays(angajat.getId()));

		if (days == 0) {

			RedirectView redirectView = new RedirectView("/fom?message=" + id);
			redirectAttributes.addFlashAttribute("error", "No more vacantion days!");

			return redirectView;
//			return "/fom?message="+id;
		}
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (startDate.equals("")) {
			RedirectView redirectView = new RedirectView("/fom?message=" + id);
			redirectAttributes.addFlashAttribute("error", "Start date is empty");

			return redirectView;
		}
		if (endDate.equals("")) {
			RedirectView redirectView = new RedirectView("/fom?message=" + id);
			redirectAttributes.addFlashAttribute("error", "End date is empty");

			return redirectView;
		}
		Date date1 = myFormat.parse(startDate);
		Date date2 = myFormat.parse(endDate);
		if (date1.after(date2)) {

			RedirectView redirectView = new RedirectView("/fom?message=" + id);
			redirectAttributes.addFlashAttribute("error", "The date is not good!");

			return redirectView;

		}
		if (date2.before(date1)) {

			RedirectView redirectView = new RedirectView("/fom?message=" + id);
			redirectAttributes.addFlashAttribute("error", "The date is not good!");

			return redirectView;
		}

		int days2 = breakService.getWorkingDaysBetweenTwoDates(date1, date2) + 1;
		if (days - days2 < 0) {

			RedirectView redirectView = new RedirectView("/fom?message=" + id);
			redirectAttributes.addFlashAttribute("error", "You have exceeded the allowed days off!");

			return redirectView;
		}

		Date resultdate = new Date(System.currentTimeMillis());
		int days3 = breakService.getWorkingDaysBetweenTwoDates(resultdate, date1) + 1;
		if (days3 > 182) {

			RedirectView redirectView = new RedirectView("/fom?message=" + id);
			redirectAttributes.addFlashAttribute("error",
					"The vacantion can be taken up to half a year in advance! You have exceeded the period with:"
							+ (days3 - 182));

			return redirectView;
		}
		if (days3 < 5) {

			RedirectView redirectView = new RedirectView("/fom?message=" + id);
			redirectAttributes.addFlashAttribute("error", "The request must be made at least 5 days before the date!");

			return redirectView;
		}

		List<Angajat> angajati2 = new ArrayList<Angajat>();
		for (AngajatDto ang : angajati) {
			angajati2.add(angajatService.convertDtoToEntity(ang));

		}

		Break break1 = new Break(startDate, endDate, Status.PENDING, angajati2);
		breakService.createBreak(break1);

//		List<Angajat> v = repo1.findAll();
//		model.addAttribute("angajati", v);
		return new RedirectView("/fom?message=" + id);
	}

	@GetMapping("/break/getBreak")
	public void getBreak(@RequestParam("id") Long id, Model model) {

		List<Break> break1 = breakService.getBreaksForAngajat(id);

//		List<Angajat> v = repo1.findAll();
		model.addAttribute("breaks", break1);
//		return break1;
//		return "addAngajat";
	}

	@GetMapping("/break/delete")
	public RedirectView removeBreak(@RequestParam("id") Long id, @RequestParam("id2") Long idAngajat, Model model) {

		try {
			breakService.deleteAngajatBreak(id);

		} catch (Exception e) {
			System.out.println("ceva a mers nsp");
		}
		breakService.deleteById(id);
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
