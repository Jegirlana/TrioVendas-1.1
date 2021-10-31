package br.ufc.web.triovendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.web.triovendas.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByCpf(String cpf);

	User findFirstByCpfAndSenha(String cpf, String senha);

}
