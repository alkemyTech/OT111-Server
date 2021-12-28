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
    @Operation(summary = "Obtiene los miembros de la organización paginados", description = "Devuelve todos los miembros de la organización, con un tamaño por defecto de pagina de 10 elementos, " +
            "ademas retorna el numero de pagina, cantidad de elementos en la pagina, cantidad de elementos total y " +
            "cantidad total de paginas. Solo accesible por un administrador")
    @GetMapping
    public CustomPage<MemberResponse> getAllMembersPage(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return memberService.getMembersPageable(pageRequest);
    }


    @Operation(summary = "Crea un nuevo miembro",
            description = "Crea un miembro de la organización, accesible por usuario regular")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MemberResponse createOrganization(@Valid @RequestBody MemberRequest request) {
        return memberService.createMember(request);
    }

    @Operation(summary = "Actualiza la información de un miembro", description = "Actualiza la información de un miembro existente dado el ID " +
            " pasado como parámetro por url, si el miembro no existe lanza un error con código de estado 404, accesible por usuario regular")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public MemberResponse updateMember(@PathVariable Long id, @Valid @RequestBody MemberRequest memberRequest) {
        return memberService.updateMember(memberRequest, id);
    }

    @Operation(summary = "Elimina un miembro existente dado el ID " +
            "pasado como parámetro por url, si el miembro no existe lanza un error con código de estado 404,solo accesible por un administrador")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteMemberById(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
