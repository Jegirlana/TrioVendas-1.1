package br.ufc.web.triovendas.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.web.triovendas.model.User;
import br.ufc.web.triovendas.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public User addUser(String cpf, String senha, String nome, String email, String telefone, String estado,
			String cidade, String complemento) {
		User user = new User(cpf, senha, nome, email, telefone, estado, cidade, complemento);
		return userRepo.save(user);
	}

	public boolean removeUser(Integer id) {
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return true;
		}

		return false;
	}

	public List<User> getUsers() {
		return userRepo.findAll();
	}

	public User getUser(Integer id) {
		return userRepo.findById(id).get();
	}

	public List<User> getUserByCpf(String cpf) {

		return userRepo.findByCpf(cpf);
	}

	@PersistenceContext
	private EntityManager entityManager;

	public List<User> getUserByQuantidade(int quantidade) {

		List<User> consulta = entityManager.createQuery("SELECT user from users user ORDER BY user.id", User.class)
				.setMaxResults(quantidade).getResultList();

		return consulta;
	}

	public User updateUser(Integer id, String cpf, String senha, String nome, String email, String telefone,
			String estado, String cidade, String complemento) {
		User userAux = userRepo.findById(id).get();

		if (userAux != null) {
			userAux.setCpf(cpf);
			userAux.setSenha(senha);
			userAux.setNome(nome);
			userAux.setEmail(email);
			userAux.setTelefone(telefone);
			userAux.setEstado(estado);
			userAux.setCidade(cidade);
			userAux.setComplemento(complemento);
			userRepo.save(userAux);
		}

		return userAux;
	}

	public User getUserByCpfAndSenha(String cpf, String senha) {
		return userRepo.findFirstByCpfAndSenha(cpf, senha);
	}

}