package com.baeldung.springsecuritythymeleaf.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baeldung.springsecuritythymeleaf.dto.AngajatDto;
import com.baeldung.springsecuritythymeleaf.model.Break;
import com.baeldung.springsecuritythymeleaf.model.enums.LegalDayOff;
import com.baeldung.springsecuritythymeleaf.model.enums.Status;
import com.baeldung.springsecuritythymeleaf.repository.AngajatRepository;
import com.baeldung.springsecuritythymeleaf.repository.BreakRepository;

@Service
public class BreakService {
	@Autowired
	private BreakRepository repository;
	@Autowired
	private AngajatService angajatService;

	public List<Break> getBreaks() {
		return repository.findAll();
	}

	public Break getBreak(Long id) {
		Optional<Break> breakOpt = repository.findById(id);
		return breakOpt.orElse(null);
	}

	public Break createBreak(Break break1) {
		return repository.save(break1);
	}

	public Break updateBreak(Break break1) {
		return repository.save(break1);
	}

	public void deleteAngajat(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
	}

	public List<Break> getBreaksForAngajatiFromEchipeSef(Long id) {
		List<AngajatDto> angajati = angajatService.getAngajatiEchipe(id);
		List<Break> vacante = new ArrayList<Break>();
		for (AngajatDto dto : angajati) {
			List<Break> bksAngajat = repository.getBreaksForAngajat(dto.getId());
			for (Break brk : bksAngajat) {
				vacante.add(brk);
			}
		}
		return vacante;
	}

	public List<Break> getBreaksForAngajatiFromEchipeSefAndSef(Long id) {
		List<AngajatDto> angajati = angajatService.getAngajatiBySefIdAndSef(id);
		List<Break> vacante = new ArrayList<Break>();
		for (AngajatDto dto : angajati) {
			List<Break> bksAngajat = repository.getBreaksForAngajat(dto.getId());
			for (Break brk : bksAngajat) {
				vacante.add(brk);
			}
		}
		return vacante;
	}

	public void updateBreakStatus(Break break1, Status status) {
		break1.setStatus(status);
		repository.save(break1);
	}

	public List<Break> getBreaksForAngajat(Long id) {
		return repository.getBreaksForAngajat(id);
	}

	public void deleteAngajatBreak(Long id) {
		repository.deleteAngajatBreak(id);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		int workDays = 0;

		// Return 0 if start and end are the same
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			return 0;
		}

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

		do {
			// excluding start date
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				++workDays;
			}
		} while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); // excluding end date

		return workDays;
	}

	public String calculateRemainingDays(Long angajatId) {
		int maxDays = 22;
		int nrDaysStat=0;
		List<Break> vacante = repository.getBreaksForAngajat(angajatId);
		for (Break vacanta : vacante) {

			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String inputString1 = vacanta.getStartDate();
			String inputString2 = vacanta.getEndDate();
			float days = 0;

			if (!inputString1.equals(inputString2)) {
				try {
					Date date1 = myFormat.parse(inputString1);
					Date date2 = myFormat.parse(inputString2);
//					long diff = date2.getTime() - date1.getTime();
//					days = (diff / (1000 * 60 * 60 * 24));
					nrDaysStat=calculateLegalDaysBetweenTwoDates( date1, date2);
					days = getWorkingDaysBetweenTwoDates(date1, date2);

				} catch (Exception e) {
				}
			} else {
				days = 1;
			}
			maxDays -= days;
			maxDays -=nrDaysStat;

		}

		return String.valueOf(maxDays);
	}
//	return a.compareTo(d) * d.compareTo(b) > 0;
	public int calculateLegalDaysBetweenTwoDates(Date date1,Date date2) throws ParseException
	{
		int nrDays=0;
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		LegalDayOff[] dayOffs = LegalDayOff.class.getEnumConstants();
		for(LegalDayOff day: dayOffs)
		{
			String d = day.getVal();
			Date date3 = myFormat.parse(d);
			if(date1.compareTo(date3) * date3.compareTo(date2) > 0)
				nrDays++;
		}
		return nrDays;
	}

}
