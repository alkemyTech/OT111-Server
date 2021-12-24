package com.alkemy.ong.model.response;

import com.alkemy.ong.model.dto.OrganizationDTO;
import lombok.*;

import java.util.Comparator;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlideResponseDTO implements Comparator<Integer> {
    private Long id;

    private String imageUrl;

    private String text;

    private Integer order;

    private OrganizationDTO organization;

    @Override
    public int compare(Integer o1, Integer o2) {
        return 0;
    }
}
