package com.alkemy.ong.auth.service;

import com.alkemy.ong.model.entity.UserEntity;
import com.alkemy.ong.model.mapper.AuthenticationMapper;
import com.alkemy.ong.model.request.security.RegisterRequest;
import com.alkemy.ong.model.response.security.RegisterResponse;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsCustomService(@Lazy AuthenticationMapper userMapper, @Lazy UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponse signupUser(RegisterRequest userToCreate) {
        var matchingUser = userRepository.findByEmail(userToCreate.getEmail());
        if (matchingUser.isPresent()) throw new IllegalArgumentException("El usuario ya existe");
        userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));
        UserEntity newUser = userMapper.registerRequestDTO2Entity(userToCreate);
        newUser = userRepository.save(newUser);
        return userMapper.entity2RegisterResponseDTO(newUser);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var foundUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " -> NOT FOUND"));

        // Authorities List
        Collection<GrantedAuthority> authorities = foundUser.getRoles().stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName()))
                .collect(Collectors.toList());

        // === Set Spring Security USER en el Context ===
        return new User(
                foundUser.getEmail(),
                foundUser.getPassword(),
                authorities
        );

    }


}


