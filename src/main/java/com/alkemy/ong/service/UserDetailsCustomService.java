package com.alkemy.ong.service;

import com.alkemy.ong.model.dto.UserDto;
import com.alkemy.ong.model.entity.UserEntity;
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

    @Autowired /*se póne de esta manera ya que si lo ponemos arriba por
     separado genera un loop y rompe el programa*/
    public UserDetailsCustomService(@Lazy UserMapper userMapper,@Lazy UserRepository userRepo){
        this.userMapper= userMapper;
        this.userRepo = userRepo;
    }
    // Registro de un nuevo usuario
    public UserDto signupUser(@Valid UserDto userToCreate) {
        // ===
        UserEntity newUser = userMapper.UserDTO2Entity(userToCreate);
        // ===

        //Busco por matcheo un usuario.
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
