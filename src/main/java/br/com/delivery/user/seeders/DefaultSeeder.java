package br.com.delivery.user.seeders;

import static br.com.delivery.user.enums.RoleType.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import br.com.delivery.user.model.User;
import br.com.delivery.user.repository.RoleRepository;
import br.com.delivery.user.repository.UserRepository;
import br.com.delivery.user.utils.Utils;

@Service
public class DefaultSeeder {

	@Autowired
	private RoleRepository roleRopositoty;

	@Autowired
	private UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSeeder.class);

	public void seedUser() {

		if (userRepository.findAll().size() == 0) {
			LOGGER.info(">>> Creating default user");
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setName("Admin User");
			user.setPassword(new BCryptPasswordEncoder().encode("123456"));
			roleRopositoty.findByName(ROLE_ADMIN.name()).ifPresent(role -> user.setRoles(Sets.newHashSet(role)));
			userRepository.save(user);
			LOGGER.info("Default user created <<<");
		}
	}

	public void seedProfiles() {
		if (roleRopositoty.count() == 0) {
			LOGGER.info(">>> Creating default roles");
			Utils.convertUserTypeRoles(ROLE_ADMIN, ROLE_MOTOBOY, ROLE_CLIENT,ROLE_PROPRIETOR).forEach(type -> roleRopositoty.save(type));
			LOGGER.info("Default roles created <<<");
		}
	}
}
