package com.alkemy.ong.auth.service;

import com.alkemy.ong.model.entity.RoleEntity;
import com.alkemy.ong.model.mapper.AuthenticationMapper;
import com.alkemy.ong.model.entity.UserEntity;
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
import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsCustomService(@Lazy AuthenticationMapper userMapper, @Lazy UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder){
        this.userMapper= userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponse signupUser(RegisterRequest userToCreate) {
        userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));
        UserEntity newUser = userMapper.registerRequestDTO2Entity(userToCreate);
        UserEntity matchingUser = userRepository.findByEmail(userToCreate.getEmail());
        if(matchingUser != null && (matchingUser.getEmail().equals(newUser.getEmail()))) {
            return userMapper.entity2RegisterResponseDTO(matchingUser);
        }
        newUser = userRepository.save(newUser);
        return userMapper.entity2RegisterResponseDTO(newUser);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity foundUser = userRepository.findByEmail(username);

         if (foundUser == null) {
              throw new UsernameNotFoundException("Username: " + username + " -> NOT FOUND");
         }

        // Authorities List
        Collection<GrantedAuthority> userGA = new ArrayList<>();
        for(RoleEntity role : foundUser.getRole()){
            userGA.add(new SimpleGrantedAuthority(role.getName()));
        }

        // === Set Spring Security USER en el Context ===
        return new User(
                foundUser.getEmail(),
                foundUser.getPassword(),
                userGA
        );

    }



}


