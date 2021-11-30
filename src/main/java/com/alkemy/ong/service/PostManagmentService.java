package com.alkemy.ong.service;

<<<<<<< Updated upstream
import com.alkemy.ong.model.dto.DTOOrganization;

public interface PostManagmentService {

    static DTOOrganization readOrganization() {
        DTOOrganization organizacion = new DTOOrganization();
=======
import com.alkemy.ong.model.dto.OrganizationDTO;

public interface PostManagmentService {

    static OrganizationDTO readOrganization() {
        OrganizationDTO organizacion = new OrganizationDTO();
>>>>>>> Stashed changes
        organizacion.setName("aca lee el nombre");
        organizacion.setPhone(1112341234);
        organizacion.setWelcomeText("alguien digame como leer la base de datos");
        organizacion.setImage("alguien digame como leer la base de datos");
        organizacion.setEmail("alguien digame como leer la base de datos");
        organizacion.setAddress("alguien digame como leer la base de datos");
        organizacion.setAboutUsText("alguien digame como leer la base de datos");

        return organizacion;
    }
}
