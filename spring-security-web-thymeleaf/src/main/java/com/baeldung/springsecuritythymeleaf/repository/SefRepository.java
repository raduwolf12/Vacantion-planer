package com.baeldung.springsecuritythymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.baeldung.springsecuritythymeleaf.model.Sef;

@Repository
public interface SefRepository extends JpaRepository<Sef, Long> {
	@Query(nativeQuery = true, value = "SELECT * FROM sefi where ceo = true")
	List<Sef> getCeo();
	
	@Query(nativeQuery = true, value = "SELECT * FROM sefi where sefi.angajat_id =?")
	Sef getSefByAngajatId(Long id);
}
