package com.alkemy.ong.model.dto;

import com.sun.istack.NotNull;
import org.springframework.lang.Nullable;

public class OrganizationDTO {
    @NotNull
    private String name;
    @NotNull
    private String image;
    @Nullable
    private String address;
    @Nullable
    private int phone;
    @NotNull
    private String email;
    @NotNull
    private String welcomeText;
    @Nullable
    private String aboutUsText;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWelcomeText() {
        return welcomeText;
    }

    public void setWelcomeText(String welcomeText) {
        this.welcomeText = welcomeText;
    }

    public String getAboutUsText() {
        return aboutUsText;
    }

    public void setAboutUsText(String aboutUsText) {
        this.aboutUsText = aboutUsText;
    }
}
