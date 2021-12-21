package com.alkemy.ong.model.response;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityUpdateResponseDTO {
        private Long id;
        private String name;
        private String content;
        private OffsetDateTime modifiedDate;
        private String modifiedBy;
}