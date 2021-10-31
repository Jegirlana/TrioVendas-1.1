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

import br.ufc.web.triovendas.model.Employee;
import br.ufc.web.triovendas.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployees() {
		return new ResponseEntity<List<Employee>>(employeeService.getEmployees(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer id) {
		return new ResponseEntity<Employee>(employeeService.getEmployee(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search", params = "cpf")
	public ResponseEntity<List<Employee>> getEmployeeByCpf(@RequestParam("cpf") String cpf) {
		return new ResponseEntity<List<Employee>>(employeeService.getEmployeeByCpf(cpf), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/page", params = "quantidade")
	public ResponseEntity<List<Employee>> getEmployeeByQuantidade(@RequestParam("quantidade") Integer quantidade) {
		return new ResponseEntity<List<Employee>>(employeeService.getEmployeeByQuantidade(quantidade), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/loginfun", params = {"cpf", "senha"})
	public ResponseEntity<Employee> getEmployeeByCpfAndSenha(@RequestParam("cpf") String cpf, @RequestParam("senha") String senha) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeByCpfAndSenha(cpf, senha), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.addEmployee(employee.getNome(), employee.getEmail(),
				employee.getCpf(), employee.getSenha(), employee.getCargo()), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<Employee> updateEmployee(@Valid @PathVariable("id") Integer id, @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(id, employee.getNome(), employee.getEmail(),
				employee.getCpf(), employee.getSenha(), employee.getCargo()), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Integer id) {
		if (employeeService.removeEmployee(id)) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}