package com.ndiaye.mvcsession.service.impl;

import com.ndiaye.mvcsession.dto.CreateUserDto;
import com.ndiaye.mvcsession.entity.Role;
import com.ndiaye.mvcsession.entity.User;
import com.ndiaye.mvcsession.exception.UserAlreadyExistsException;
import com.ndiaye.mvcsession.repository.RoleRepository;
import com.ndiaye.mvcsession.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User saveUser(CreateUserDto userDto) {
        User retrievedUser = userRepository.findByEmail(userDto.getEmail());
        if (retrievedUser != null)
            throw new UserAlreadyExistsException("User already exists for given email " + userDto.getEmail());
        User u = new User();
        u.setFirstname(userDto.getFirstname());
        u.setLastname(userDto.getLastname());
        u.setEmail(userDto.getEmail());
        u.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByRole("ROLE_USER");
        u.setRoles(new HashSet<>(Arrays.asList(role)));
        return userRepository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = userRepository.findByEmail(email);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid email and/or password");
        }
        List<GrantedAuthority> authorities = getUserAuthorities(u.getRoles());
        return buildUserForAuthentication(u, authorities);
    }

    private UserDetails buildUserForAuthentication(User u, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), authorities);
    }

    private List<GrantedAuthority> getUserAuthorities(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach(
                (role) -> roles.add(new SimpleGrantedAuthority(role.getRole()))
        );
        return new ArrayList<>(roles);
    }
}
