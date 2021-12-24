package com.alkemy.ong.service;

import com.alkemy.ong.model.request.SlideRequestDTO;
import com.alkemy.ong.model.response.SlideResponseDTO;

public interface SlideService {

    SlideResponseDTO findSlideById(Long id);

    void updateSlide(SlideRequestDTO request, Long id);

    void deleteSlide(Long id);

}
