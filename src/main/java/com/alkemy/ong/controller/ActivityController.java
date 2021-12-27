package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.ActivityRequest;
import com.alkemy.ong.model.request.ActivityUpdateRequestDTO;
import com.alkemy.ong.model.response.ActivityResponseDTO;
import com.alkemy.ong.model.response.ActivityUpdateResponseDTO;
import com.alkemy.ong.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
@Tag(name = "Actividades")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    @Operation(summary = "Crear una nueva actividad")
    @ResponseStatus(HttpStatus.CREATED)
    public ActivityResponseDTO createActivity(@CurrentSecurityContext(expression = "authentication")
                                                                          Authentication authentication,
                                                              @RequestBody ActivityRequest request) {
        String userName = authentication.getName();

        return activityService.createActivity(request, userName);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una actividad",
            description = "Actualiza la actividad existente dado el ID pasado como parámetro por url, " +
                    "y si la actividad a actualizar no existe se lanza un error con código de estado 404")
    @ResponseStatus(HttpStatus.OK)
    public ActivityUpdateResponseDTO updateActivity(@CurrentSecurityContext(expression = "authentication")
                                                                            Authentication authentication,
                                                                    @RequestBody ActivityUpdateRequestDTO request,
                                                                    @PathVariable Long id) {
        String userName = authentication.getName();

        return activityService.updateActivity(request, id, userName);


    }

}
