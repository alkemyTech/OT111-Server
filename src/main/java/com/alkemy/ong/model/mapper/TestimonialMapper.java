package com.alkemy.ong.model.mapper;

import com.alkemy.ong.model.entity.TestimonialEntity;
import com.alkemy.ong.model.request.TestimonialRequestDTO;
import com.alkemy.ong.model.response.TestimonialResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper{

    public TestimonialResponseDTO entity2DTO(TestimonialEntity ent) {
        TestimonialResponseDTO dto = new TestimonialResponseDTO();
        dto.setId(ent.getId());
        dto.setName(ent.getName());
        dto.setContent(ent.getContent());
        dto.setImage(ent.getImage());
        return dto;
    }

    public TestimonialEntity dto2Entity(TestimonialRequestDTO dto) {
        TestimonialEntity ent = new TestimonialEntity();
        ent.setName(dto.getName());
        ent.setImage(dto.getImage());
        ent.setContent(dto.getContent());
        return ent;
    }
    /*
    public TestimonialResponseDTO requestToResponse(TestimonialEntity requestDTO){
        return entity2DTO(dto2Entity(requestDTO));
    }*/

}
