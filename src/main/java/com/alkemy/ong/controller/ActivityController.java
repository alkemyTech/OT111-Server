package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.ActivityRequest;
import com.alkemy.ong.model.request.ActivityUpdateRequestDTO;
import com.alkemy.ong.model.response.ActivityResponseDTO;
import com.alkemy.ong.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
@Tag(name = "Actividades")
@Validated
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    @Operation(summary = "Crear una nueva actividad")
    @ResponseStatus(HttpStatus.CREATED)
    public ActivityResponseDTO createActivity(@Valid @RequestBody ActivityRequest request) {
        return activityService.createActivity(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una actividad",
            description = "Actualiza la actividad existente dado el ID pasado como parámetro por url, " +
                    "y si la actividad a actualizar no existe se lanza un error con código de estado 404")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ActivityResponseDTO updateActivity(@Valid @RequestBody ActivityUpdateRequestDTO request,
                                              @PathVariable Long id) {
        return activityService.updateActivity(request, id);


    }

}