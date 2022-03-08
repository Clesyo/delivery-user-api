package br.com.delivery.user.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import br.com.delivery.user.dto.UserDto;
import br.com.delivery.user.exception.InvalidException;
import br.com.delivery.user.form.UserForm;
import br.com.delivery.user.interfaces.IUserService;
import br.com.delivery.user.model.User;
import br.com.delivery.user.repository.RoleRepository;
import br.com.delivery.user.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDto findById(Long id) {
		var user = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para ID informado."));
		return UserDto.convertTo(user);
	}

	@Override
	public User findByEmail(String email) {
		var user = userRepository.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para email informado."));
		return user;
	}

	@Override
	public UserDto save(UserForm form) {
		userRepository.findByEmail(form.getEmail()).ifPresent(user -> {
			throw new EntityNotFoundException("Já existe um usuário com email fornecido.");
		});
		if (form.getRoles().isEmpty())
			throw new InvalidException("roles", "Usuário deve possuir no mínimo uma função.");
		var user = form.toUser();
		var roleFound = form.getRoles().stream().map(role -> {
			return roleRepository.findByName(role.getName())
					.orElseThrow(() -> new EntityNotFoundException("Função informada não é válida."));
		}).toList();
		user.setRoles(Sets.newHashSet(roleFound));
		userRepository.save(user);
		return UserDto.convertTo(user);
	}
}
