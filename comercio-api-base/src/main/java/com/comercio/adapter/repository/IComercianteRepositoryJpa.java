package com.comercio.adapter.repository;

import com.comercio.domain.model.Comerciante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IComercianteRepositoryJpa extends JpaRepository<Comerciante, Long> {

	@Query("SELECT c FROM Comerciante c WHERE " +
		       "(:razonSocial IS NULL OR LOWER(c.razonSocial) LIKE LOWER(CONCAT('%', :razonSocial, '%'))) AND " +
		       "(:estado IS NULL OR c.estado = :estado)")
	Page<Comerciante> findByFilters(@Param("razonSocial") String razonSocial,
		                                 @Param("estado") String estado,
		                                 Pageable pageable);
	
    List<Comerciante> findByEstado(String estado);
    
    @Query("SELECT DISTINCT c.municipio FROM Comerciante c")
    List<String> findDistinctMunicipios();
}
