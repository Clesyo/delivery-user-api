package br.com.delivery.user.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.delivery.user.dto.UserDto;
import br.com.delivery.user.interfaces.IUserService;
import br.com.delivery.user.model.User;
import br.com.delivery.user.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto findById(Long id) {
		var user = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para ID informado."));
		return UserDto.convertTo(user);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		var user = userRepository.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para email informado."));
		return user;
	}
}
