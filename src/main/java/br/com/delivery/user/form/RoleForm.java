package br.com.delivery.user.form;

import java.util.Arrays;

import br.com.delivery.user.model.Role;

public class RoleForm {

	private String name;

	public Role toRole(Role... roles) {
		var role = new Role();

		var list = Arrays.asList(roles);
		if (!list.isEmpty())
			role = list.get(0);
		role.setName(this.name);
		return role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
