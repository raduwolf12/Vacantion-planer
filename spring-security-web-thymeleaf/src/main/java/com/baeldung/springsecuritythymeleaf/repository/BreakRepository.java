package com.baeldung.springsecuritythymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.baeldung.springsecuritythymeleaf.model.Break;

@Repository
public interface BreakRepository extends JpaRepository<Break, Long> {
	@Query(nativeQuery = true, value = "SELECT breaks.* FROM breaks INNER JOIN breaks_angajati ON breaks.breaks_id=breaks_angajati.break_breaks_id where breaks_angajati.angajati_angajat_id=?")
	List<Break> getBreaksForAngajat(Long id);
	@Query(nativeQuery = true, value = "DELETE FROM public.breaks_angajati WHERE breaks_angajati.break_breaks_id=?")
	Object deleteAngajatBreak(Long id);
	
	
}
