package br.com.delivery.user.interfaces;

import br.com.delivery.user.dto.UserDto;
import br.com.delivery.user.model.User;

public interface IUserService {

	UserDto findById(Long id);
	User findByEmail(String email);
	
	
}
