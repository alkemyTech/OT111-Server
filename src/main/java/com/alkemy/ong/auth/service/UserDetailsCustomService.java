package com.alkemy.ong.auth.service;

import com.alkemy.ong.auth.dto.UserDto;
import com.alkemy.ong.auth.entity.UserEntity;
import com.alkemy.ong.auth.mapper.UserMapper;
import com.alkemy.ong.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserDetailsCustomService implements UserDetailsService {


    private UserRepository userRepo;

    private UserMapper userMapper;

    @Autowired
    public UserDetailsCustomService(@Lazy UserMapper userMapper,@Lazy UserRepository userRepo){
        this.userMapper= userMapper;
        this.userRepo = userRepo;
    }





    // Signup New User:
    public UserDto signupUser(@Valid UserDto userToCreate) {
        // ===
        UserEntity newUser = userMapper.UserDTO2Entity(userToCreate);
        // ===

        UserEntity matchingUser = userRepo.findByUsername(userToCreate.getUsername());
        if(matchingUser != null && (matchingUser.getUsername().equals(newUser.getUsername()))) {
            // NO LO CREA, PERO NO ENVIA "Already Exists"
            // En Controller verificamos TRUE o FALSE y Mandamos ResponseEntity segun corresponda
            return userMapper.entity2DTO(matchingUser);
        }
        newUser = userRepo.save(newUser);

        return userMapper.entity2DTO(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
