package com.security.jwt.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.jwt.dao.RoleDao;
import com.security.jwt.dao.UserDao;
import com.security.jwt.entity.Role;
import com.security.jwt.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	public void initRoleAndUser() {
//
//		Role adminRole = new Role();
//		adminRole.setRoleName("Admin");
//		adminRole.setRoleDescription("Admin role");
//		roleDao.save(adminRole);
//
//		Role userRole = new Role();
//		userRole.setRoleName("User");
//		userRole.setRoleDescription("Default role for newly created record");
//		roleDao.save(userRole);
//
//		User adminUser = new User();
//		adminUser.setUserName("admin");
//		adminUser.setUserPassword(getEncodedPassword("admin"));
//		adminUser.setUserFirstName("admin");
//		adminUser.setUserLastName("admin");
//		Set<Role> adminRoles = new HashSet<>();
//		adminRoles.add(adminRole);
//		adminUser.setRole(adminRoles);
//		userDao.save(adminUser);
//
//	}

	public User registerNewUser(User user) {
		Role role = roleDao.findById("User").get();

		Set<Role> userRoles = new HashSet<>();
		userRoles.add(role);
		user.setRole(userRoles);
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));

		return userDao.save(user);
	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}
}
