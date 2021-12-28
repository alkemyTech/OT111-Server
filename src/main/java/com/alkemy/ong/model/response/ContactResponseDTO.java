package com.alkemy.ong.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponseDTO {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String message;
}
