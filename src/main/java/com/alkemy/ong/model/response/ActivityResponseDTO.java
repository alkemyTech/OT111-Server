package com.alkemy.ong.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponseDTO {
        private Long id;
        private String name;
        private String content;
}