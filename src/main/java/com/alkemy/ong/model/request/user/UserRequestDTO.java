package com.alkemy.ong.model.request.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

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
