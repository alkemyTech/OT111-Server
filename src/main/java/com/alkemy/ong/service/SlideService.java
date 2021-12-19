package com.alkemy.ong.service;


import com.alkemy.ong.model.request.SlideRequestDTO;
import com.alkemy.ong.model.response.SlideResponseDTO;

import java.util.List;

public interface SlideService {
    SlideResponseDTO saveSlide(SlideRequestDTO request);
    List<SlideResponseDTO> getSlides();
}
