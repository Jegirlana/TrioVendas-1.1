package br.ufc.web.triovendas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.web.triovendas.model.Furniture;
import br.ufc.web.triovendas.service.FurnitureService;


@RestController
@CrossOrigin
@RequestMapping(path = "/api/furnitures")
public class FurnitureController {

	@Autowired
	FurnitureService furnitureService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Furniture>> getFurnitures() {
		return new ResponseEntity<List<Furniture>>(furnitureService.getFurnitures(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<Furniture> getFurnitures(@PathVariable("id") Integer id) {
		return new ResponseEntity<Furniture>(furnitureService.getFurniture(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search", params = "serie")
	public ResponseEntity<List<Furniture>> getFurnitureBySerie(@RequestParam("serie") String serie) {
		return new ResponseEntity<List<Furniture>>(furnitureService.getFurnitureBySerie(serie), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/tipo", params = "tipo")
	public ResponseEntity<List<Furniture>> getFurnitureByTipo(@RequestParam("tipo") String tipo) {
		return new ResponseEntity<List<Furniture>>(furnitureService.getFurnitureByTipo(tipo), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/quant", params = "quant")
	public ResponseEntity<List<Furniture>> getFurnitureByQuantidade(@RequestParam("quant") Integer quant) {
		return new ResponseEntity<List<Furniture>>(furnitureService.getFurnitureByQuantidade(quant), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Furniture> addFurniture(@Valid @RequestBody Furniture furniture) {
		return new ResponseEntity<Furniture>(
				furnitureService.addFurniture(furniture.getNome(), furniture.getDescricao(), 
						furniture.getSerie(), furniture.getTipo(), furniture.getPreco(), 
						furniture.getQuantidade()),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<Furniture> updateFurniture(@Valid @PathVariable("id") Integer id, @RequestBody Furniture furniture) {
		return new ResponseEntity<Furniture>(furnitureService.updateFurniture(id, furniture.getNome(), 
				furniture.getDescricao(), furniture.getSerie(), furniture.getTipo(), 
				furniture.getPreco(), furniture.getQuantidade()), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<Void> deleteFurniture(@PathVariable("id") Integer id) {
		if (furnitureService.removeFurniture(id)) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
