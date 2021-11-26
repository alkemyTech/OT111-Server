package com.alkemy.ong.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class userService {

    //Metodo de registro
    @Transactional
    public void singUp (String firstName, String lastName, String email, String password,Integer roleId) throws errorService{

        //Validacion de datos(falta foto, ver como hacer)
        if (firstName == null || firstName.isEmpty()){
            throw new errorService("First name cannot be null or empty");
        }
        if (lastName == null || lastName.isEmpty()){
            throw new errorService("Last name cannot be null or empty");
        }
        if (email == null || email.isEmpty()){
            throw new errorService("Email cannot be null or empty");
        }
        if (password == null || password.isEmpty()){
            throw new errorService("Last name cannot be null or empty");
        }
        if (roleId == null || roleId.equals(" ")){
            throw new errorService("Role Id cannot be null or empty");
        }

    }

    //Falta crear la entidad user (con getters and setters/lombok)
    user user = new user();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);

    String encrypted = new BCryptPasswordEncoder().encode(password); //Creo una variable para encriptar clave

    user.setPassword(encrypted); //seteo clave encriptada, no directamente password
    user.setRoleId(roleId);

    userRepository.save(user);

}
