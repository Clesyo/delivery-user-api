package br.com.delivery.user.dto;

import java.util.Set;

import br.com.delivery.user.model.User;

public class UserDto {

	private String publicId;
	private String name;
	private String login;
	private String type;
	private Set<RoleDto> roles;

	public UserDto(User user) {
		this.publicId = user.getPublicId();
		this.name = user.getName();
		this.login = user.getEmail();
		this.type = user.getType().name();
		this.roles = RoleDto.convertTo(user.getRoles());
	}

	public static UserDto convertTo(User user) {
		return new UserDto(user);
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
}
