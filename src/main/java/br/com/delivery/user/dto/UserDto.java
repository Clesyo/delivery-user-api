package br.com.delivery.user.dto;

import java.util.Set;

import br.com.delivery.user.model.User;

public class UserDto {

	private String name;
	private String login;
	private Set<RoleDto> roles;

	public UserDto(User user) {
		this.name = user.getName();
		this.login = user.getEmail();
		this.roles = RoleDto.convertTo(user.getRoles());
	}

	public static UserDto convertTo(User user) {
		return new UserDto(user);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

}
