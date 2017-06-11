package org.npj.campanha.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CampanhaDao extends CrudRepository<Campanha, Long> {
	
	Campanha findById(Long Id);
	List<Campanha> findAll();
	List<Campanha> findByDataInicialAndDataFinal(Date Inicial, Date dataFinal);
}
