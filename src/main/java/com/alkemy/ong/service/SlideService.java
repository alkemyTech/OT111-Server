package com.alkemy.ong.service;


import com.alkemy.ong.model.request.SlideRequestDTO;
import com.alkemy.ong.model.response.SlideResponseDTO;

public interface SlideService {
    SlideResponseDTO saveSlide(SlideRequestDTO request);
}
