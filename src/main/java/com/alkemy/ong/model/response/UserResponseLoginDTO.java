package com.alkemy.ong.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponseLoginDTO {

    private String email;
    private String password;
    private String jwt;
}
