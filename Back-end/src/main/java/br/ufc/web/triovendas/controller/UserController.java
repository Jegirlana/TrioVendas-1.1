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

import br.ufc.web.triovendas.model.User;
import br.ufc.web.triovendas.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/users")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
		return new ResponseEntity<User>(userService.getUser(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search", params = "cpf")
	public ResponseEntity<List<User>> getUserByCpf(@RequestParam("cpf") String cpf) {
		return new ResponseEntity<List<User>>(userService.getUserByCpf(cpf), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/page", params = "quantidade")
	public ResponseEntity<List<User>> getUserByQuantidade(@RequestParam("quantidade") Integer quantidade) {
		return new ResponseEntity<List<User>>(userService.getUserByQuantidade(quantidade), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		return new ResponseEntity<User>(userService.addUser(user.getCpf(), user.getSenha(), user.getNome(),
				user.getEmail(), user.getTelefone(), user.getEstado(), user.getCidade(), user.getComplemento()),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login", params = {"cpf", "senha"})
	public ResponseEntity<User> getUserByCpfAndSenha(@RequestParam("cpf") String cpf, @RequestParam("senha") String senha) {
		return new ResponseEntity<User>(userService.getUserByCpfAndSenha(cpf, senha), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<User> updateUser(@Valid @PathVariable("id") Integer id, @RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(id, user.getCpf(), user.getSenha(), user.getNome(),
				user.getEmail(), user.getTelefone(), user.getEstado(), user.getCidade(), user.getComplemento()),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
		if (userService.removeUser(id)) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}