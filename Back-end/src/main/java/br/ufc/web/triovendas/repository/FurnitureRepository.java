package br.ufc.web.triovendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.web.triovendas.model.Furniture;

public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {
	
	List<Furniture> findBySerie(String serie);
	List<Furniture> findByTipo(String tipo);

}