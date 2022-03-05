package br.com.delivery.user.dto;

import java.util.Set;

import com.google.common.collect.Sets;

import br.com.delivery.user.model.Role;

public class RoleDto {

	private String name;

	public RoleDto(Role role) {
		this.name = role.getName();
	}

	public static Set<RoleDto> convertTo(Set<Role> roles) {
		return Sets.newHashSet(roles.stream().map(RoleDto::new).toList());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
