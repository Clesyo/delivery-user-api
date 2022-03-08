package br.com.delivery.user.form;

import java.util.Arrays;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.delivery.user.enums.UserType;
import br.com.delivery.user.model.Role;
import br.com.delivery.user.model.User;

public class UserForm {

	@NotEmpty(message = "Nome não pode ser vazio.")
	private String name;
	
	@NotEmpty(message = "Email não pode ser vazio.")
	@Email(message = "Informe um email válido.")
	private String email;
	
	@NotEmpty(message = "Senha não pode ser vazio.")
	@Min(value = 4, message = "Quantidade de caracteres não pode ser inferior a 4")
	private String password;
	
	@NotEmpty(message = "Tipo não pode ser vazio.")
	private String type;
	
	private Set<RoleForm> roles;
	
	public User toUser(User...users) {
		var user = new User();
		var list = Arrays.asList(users);
		if(!list.isEmpty())
			user = list.get(0);
		user.setEmail(this.email);
		user.setPassword(new BCryptPasswordEncoder().encode(this.password));
		user.setName(this.name);
		user.setType(UserType.valueOf(this.type));
		
		return user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<RoleForm> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleForm> roles) {
		this.roles = roles;
	}
}
