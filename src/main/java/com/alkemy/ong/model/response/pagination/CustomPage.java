package com.alkemy.ong.model.response.pagination;


import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class CustomPage<T> {

    List<T> content;

    CustomPageable pageable;

    public CustomPage(Page<T> page) {
        this.content = page.getContent();
        this.pageable = new CustomPageable(
                page.getPageable().getPageNumber(),
                page.getNumberOfElements(),
                page.getTotalElements(),
                page.getTotalPages());
    }
}
