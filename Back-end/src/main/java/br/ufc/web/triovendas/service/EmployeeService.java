package br.ufc.web.triovendas.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.web.triovendas.model.Employee;
import br.ufc.web.triovendas.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepo;

	public Employee addEmployee(String nome, String email, String cpf, String senha, String cargo) {
		Employee employee = new Employee(nome, email, cpf, senha, cargo);
		return employeeRepo.save(employee);
	}

	public boolean removeEmployee(Integer id) {
		if (employeeRepo.existsById(id)) {
			employeeRepo.deleteById(id);
			return true;
		}

		return false;
	}

	public List<Employee> getEmployees() {
		return employeeRepo.findAll();
	}

	public Employee getEmployee(Integer id) {
		return employeeRepo.findById(id).get();
	}

	public List<Employee> getEmployeeByCpf(String cpf) {
		return employeeRepo.findByCpf(cpf);
	}

	@PersistenceContext
	private EntityManager entityManager;

	public List<Employee> getEmployeeByQuantidade(int quantidade) {
		List<Employee> consulta = entityManager
				.createQuery("SELECT employee from employees employee ORDER BY employee.id", Employee.class)
				.setMaxResults(quantidade).getResultList();
		return consulta;
	}

	public Employee updateEmployee(Integer id, String nome, String email, String cpf, String senha, String cargo) {
		Employee employeeAux = employeeRepo.findById(id).get();

		if (employeeAux != null) {
			employeeAux.setNome(nome);
			employeeAux.setEmail(email);
			employeeAux.setCpf(cpf);
			employeeAux.setSenha(senha);
			employeeAux.setCargo(cargo);
			employeeRepo.save(employeeAux);

		}

		return employeeAux;
	}
	
	public Employee getEmployeeByCpfAndSenha(String cpf, String senha) {
		return employeeRepo.findFirstByCpfAndSenha(cpf, senha);
	}
}