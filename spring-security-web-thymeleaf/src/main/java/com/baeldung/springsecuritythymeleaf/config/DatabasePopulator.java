package com.baeldung.springsecuritythymeleaf.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baeldung.springsecuritythymeleaf.model.Angajat;
import com.baeldung.springsecuritythymeleaf.model.Echipa;
import com.baeldung.springsecuritythymeleaf.model.Sef;
import com.baeldung.springsecuritythymeleaf.repository.AngajatRepository;
import com.baeldung.springsecuritythymeleaf.repository.EchipaRepository;
import com.baeldung.springsecuritythymeleaf.repository.SefRepository;

@Component
public class DatabasePopulator {

//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	private AngajatRepository angajatRepository;
	
	@Autowired
	private EchipaRepository echipaRepository;
	
	@Autowired
	private SefRepository sefRepository; 

	@PostConstruct
	public void populateDatabase() {
		if (this.angajatRepository.count() == 0) {
			for (int counter = 0; counter < 10; counter++) {

				Angajat angajat = new Angajat("angajat"+counter,"email"+counter,"pass"+counter);
				angajatRepository.save(angajat);
				
				List<Angajat> angajati = new ArrayList<Angajat>();
				angajati.add(angajat);
				
				Echipa echipa = new Echipa(Long.valueOf(counter), "echipa"+counter,angajati);
				echipaRepository.save(echipa);
				
				List<Echipa> echipe = new ArrayList<Echipa>();
				echipe.add(echipa);
				
				if(counter == 9)
				{
					Sef sef = new Sef(Long.valueOf(counter), true, angajat, echipe);
					sefRepository.save(sef);
				}else
					{
					Sef sef = new Sef(Long.valueOf(counter), false, angajat, echipe);
					sefRepository.save(sef);
						
				}
			}
		}
	}

}
