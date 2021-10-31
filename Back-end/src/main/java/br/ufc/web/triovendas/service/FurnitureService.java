package br.ufc.web.triovendas.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.web.triovendas.model.Furniture;
import br.ufc.web.triovendas.repository.FurnitureRepository;

@Service
public class FurnitureService {
	
	@Autowired
	FurnitureRepository furnitureRepo;

	public Furniture addFurniture(String nome, String descricao, String serie, String tipo, String preco,
			String quantidade) {
		Furniture furniture = new Furniture(nome, descricao, serie, tipo, preco, quantidade);
		return furnitureRepo.save(furniture);
	}

	public boolean removeFurniture(Integer id) {
		if (furnitureRepo.existsById(id)) {
			furnitureRepo.deleteById(id);
			return true;
		}

		return false;
	}

	public List<Furniture> getFurnitures() {
		return furnitureRepo.findAll();
	}

	public Furniture getFurniture(Integer id) {
		return furnitureRepo.findById(id).get();
	}

	public List<Furniture> getFurnitureBySerie(String serie) {

		return furnitureRepo.findBySerie(serie);
	}
	
	public List<Furniture> getFurnitureByTipo(String tipo) {

		return furnitureRepo.findByTipo(tipo);
	}

	@PersistenceContext
	private EntityManager entityManager;

	public List<Furniture> getFurnitureByQuantidade(int quant) {

		List<Furniture> consulta = entityManager.createQuery("SELECT furniture from furnitures furniture ORDER BY furniture.id", Furniture.class)
				.setMaxResults(quant).getResultList();

		return consulta;
	}

	public Furniture updateFurniture(Integer id, String nome, String descricao, String serie, String tipo, 
			String preco, String quantidade) {
		Furniture furnitureAux = furnitureRepo.findById(id).get();

		if (furnitureAux != null) {
			furnitureAux.setNome(nome);
			furnitureAux.setDescricao(descricao);
			furnitureAux.setSerie(serie);
			furnitureAux.setTipo(tipo);
			furnitureAux.setPreco(preco);
			furnitureAux.setQuantidade(quantidade);
			furnitureRepo.save(furnitureAux);
		}

		return furnitureAux;
	}

}
