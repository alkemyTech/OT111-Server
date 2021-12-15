package com.alkemy.ong.model.request.user;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@Data
public class UserUpdateDTO {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String email;

    @NotEmpty
    private String photo;

    @NotEmpty
    private List<Integer> roles;

}
