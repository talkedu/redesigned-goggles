package org.npj.sociotorcedor.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SocioTorcedorDao extends CrudRepository<SocioTorcedor, Long>{
	
	List<SocioTorcedor> findAll();
	SocioTorcedor findByEmail(String email);
	
}
