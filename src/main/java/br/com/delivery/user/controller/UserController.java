package br.com.delivery.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.delivery.user.dto.UserDto;
import br.com.delivery.user.form.UserForm;
import br.com.delivery.user.interfaces.IUserService;
import br.com.delivery.user.model.User;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private IUserService userService;

	@PostMapping
	@ResponseStatus(CREATED)
	public UserDto save(@RequestBody @Valid UserForm form) {
		return userService.save(form);
	}

	@GetMapping("/{id}")
	@ResponseStatus(OK)
	public UserDto findByid(@PathVariable Long id) {
		return userService.findById(id);
	}

	@GetMapping("/search")
	@ResponseStatus(OK)
	public User findByEmail(@RequestParam String email) {
		return userService.findByEmail(email);
	}
}
