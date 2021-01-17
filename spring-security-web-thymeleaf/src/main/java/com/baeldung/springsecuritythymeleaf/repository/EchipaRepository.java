package com.baeldung.springsecuritythymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.baeldung.springsecuritythymeleaf.model.Echipa;

@Repository
public interface EchipaRepository extends JpaRepository<Echipa, Long> {

	@Query(nativeQuery = true, value = "select echipe.* from echipe join echipe_angajati ON echipe_angajati.echipa_echipa_id = echipe.echipa_id \r\n"
			+ "			join angajati ON angajati.angajat_id = echipe_angajati.angajati_angajat_id WHERE angajati.angajat_id=?")
	List<Echipa> getEchipeAngajat(Long angajatId);
	
	@Query(nativeQuery = true, value = "select echipe.* from echipe join sefi_echipe on echipe.echipa_id = sefi_echipe.echipe_echipa_id\r\n" + 
			"			join sefi on sefi.sef_id = sefi_echipe.sef_sef_id where sefi.angajat_id=?")
	List<Echipa> getEchipeMenagedByAngajat(Long angajatId);
	
	
}
