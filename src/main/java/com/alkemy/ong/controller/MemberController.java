package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.MemberRequest;
import com.alkemy.ong.model.response.MemberResponse;
import com.alkemy.ong.model.response.pagination.CustomPage;
import com.alkemy.ong.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Tag(name = "Miembros")
@Validated
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtiene los miembros de la organizacion paginados")
    @GetMapping
    public CustomPage<MemberResponse> getAllMembersPage(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return memberService.getMembersPageable(pageRequest);
    }


    @Operation(summary = "Crea un nuevo miembro",
            description = "Crea un miembro como usuario normal")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MemberResponse createOrganization(@Valid @RequestBody MemberRequest request) {
        return memberService.createMember(request);
    }
}
