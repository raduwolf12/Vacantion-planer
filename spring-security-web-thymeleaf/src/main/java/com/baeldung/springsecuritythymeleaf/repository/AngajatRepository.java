package com.baeldung.springsecuritythymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baeldung.springsecuritythymeleaf.model.Angajat;

@Repository
public interface AngajatRepository extends JpaRepository<Angajat, Long> {
	@Query(nativeQuery = true, value = "SELECT case when COUNT(*) > 0  then 'true' else 'false' end AS offer_count FROM angajati WHERE angajati.angajat_name=? AND angajati.\"password\"= ?")
	Boolean login(String name, String password);

	@Query(nativeQuery = true, value = "SELECT angajat_id  FROM  angajati  WHERE angajati.angajat_name=? AND angajati.\"password\"= ?")
	Long getIdByNameAndPassword(String name, String password);

	@Query(nativeQuery = true, value = "SELECT *  FROM  angajati  WHERE angajati.email=?")
	Angajat getAngajatByEmail(String email);
	
	@Query(nativeQuery = true, value = "SELECT *  FROM  angajati  WHERE angajati.email=? AND angajati.\"password\"= ?")
	Angajat getAngajatByEmailAndPassword(String email,String password);
	

	@Query(nativeQuery = true, value = "SELECT angajati.*\r\n" + "FROM sefi\r\n"
			+ "INNER JOIN sefi_echipe ON sefi.sef_id=sefi_echipe.sef_sef_id \r\n"
			+ "join echipe on echipe.echipa_id=sefi_echipe.echipe_echipa_id \r\n"
			+ "join echipe_angajati on echipe_angajati.echipa_echipa_id =  echipe.echipa_id\r\n"
			+ "join angajati on angajati.angajat_id = echipe_angajati.angajati_angajat_id\r\n"
			+ "where sefi.angajat_id=:id and angajati.angajat_id!=:id")
	List<Angajat> getAngajatiBySefId(@Param("id")Long id);
	
	@Query(nativeQuery = true, value = "SELECT angajati.*\r\n" + "FROM sefi\r\n"
			+ "INNER JOIN sefi_echipe ON sefi.sef_id=sefi_echipe.sef_sef_id \r\n"
			+ "join echipe on echipe.echipa_id=sefi_echipe.echipe_echipa_id \r\n"
			+ "join echipe_angajati on echipe_angajati.echipa_echipa_id =  echipe.echipa_id\r\n"
			+ "join angajati on angajati.angajat_id = echipe_angajati.angajati_angajat_id\r\n"
			+ "where sefi.angajat_id=:id")
	List<Angajat> getAngajatiBySefIdAndSef(@Param("id")Long id);
	
	@Query(nativeQuery = true, value = "SELECT angajati.*\r\n" + "FROM sefi\r\n"
			+ "INNER JOIN sefi_echipe ON sefi.sef_id=sefi_echipe.sef_sef_id \r\n"
			+ "join echipe on echipe.echipa_id=sefi_echipe.echipe_echipa_id \r\n"
			+ "join echipe_angajati on echipe_angajati.echipa_echipa_id =  echipe.echipa_id\r\n"
			+ "join angajati on angajati.angajat_id = echipe_angajati.angajati_angajat_id\r\n"
			+ "where sefi.angajat_id=:id")
	List<Angajat> getAngajatiBySefIdIncludingSef(@Param("id")Long id);
	
}
