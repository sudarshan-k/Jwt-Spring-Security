package com.security.jwt.service;

import com.security.jwt.entity.Role;
import com.security.jwt.entity.User;
import com.security.jwt.repository.RoleRepository;
import com.security.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void initRolesAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("user");
        userRole.setRoleDescription("Default role for newly created user");
        roleRepository.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin");
        adminUser.setPassword(getEncodedPassword("Admin@admin123"));
        adminUser.setEmail("admin123@gmail.com");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
