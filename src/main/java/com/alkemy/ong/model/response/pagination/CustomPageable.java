package com.alkemy.ong.model.response.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomPageable {

    private int pageNumber;
    private int numberOfElements;
    private long totalElements;
    private int totalPages;

}
