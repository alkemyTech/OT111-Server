package com.alkemy.ong.service;

import com.alkemy.ong.model.request.SlideRequestDTO;
import com.alkemy.ong.model.response.SlideListadoResponseDTO;
import com.alkemy.ong.model.response.SlideResponseDTO;

import java.util.List;

public interface SlideService {

    SlideResponseDTO findSlideById(Long id);

    void updateSlide(SlideRequestDTO request, Long id);

    void deleteSlide(Long id);

    List<SlideListadoResponseDTO> getSlides();

    SlideResponseDTO saveSlide(SlideRequestDTO request);

}
