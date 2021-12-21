package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.ActivityRequestDTO;
import com.alkemy.ong.model.request.ActivityUpdateRequestDTO;
import com.alkemy.ong.model.response.ActivityResponseDTO;
import com.alkemy.ong.model.response.ActivityUpdateResponseDTO;
import com.alkemy.ong.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
@Tag(name = "Actividades")
public class ActivityController {

    @Autowired
    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponseDTO> createActivity(@CurrentSecurityContext(expression = "authentication")
                                                                          Authentication authentication,
                                                              @RequestBody ActivityRequestDTO request) {
        String userName = authentication.getName();
        ActivityResponseDTO response = activityService.createActivity(request, userName);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una actividad",
            description = "Actualiza la actividad existente dado el ID pasado como parámetro por url, " +
                    "y si la actividad a actualizar no existe se lanza un error con código de estado 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Actividad actualizada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ActivityUpdateRequestDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    public ResponseEntity<ActivityUpdateResponseDTO> updateActivity(@CurrentSecurityContext(expression = "authentication")
                                                                            Authentication authentication,
                                                                    @RequestBody ActivityUpdateRequestDTO request,
                                                                    @PathVariable Long id) {
        String userName = authentication.getName();

        ActivityUpdateResponseDTO updateActivity = activityService.updateActivity(request, id, userName);

        return ResponseEntity.ok(updateActivity);
    }

}
