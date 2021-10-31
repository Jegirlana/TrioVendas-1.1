package br.ufc.web.triovendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.web.triovendas.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findByCpf(String cpf);
	Employee findFirstByCpfAndSenha(String cpf, String senha);
}
