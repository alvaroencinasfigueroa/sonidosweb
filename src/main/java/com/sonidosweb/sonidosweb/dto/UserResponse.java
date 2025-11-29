package com.sonidosweb.sonidosweb.dto;

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private boolean hasAndroidLicense;
    private boolean hasDesktopLicense;

    // Constructores
    public UserResponse() {}

    public UserResponse(Long id, String name, String email, boolean hasAndroidLicense, boolean hasDesktopLicense) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hasAndroidLicense = hasAndroidLicense;
        this.hasDesktopLicense = hasDesktopLicense;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHasAndroidLicense() {
        return hasAndroidLicense;
    }

    public void setHasAndroidLicense(boolean hasAndroidLicense) {
        this.hasAndroidLicense = hasAndroidLicense;
    }

    public boolean isHasDesktopLicense() {
        return hasDesktopLicense;
    }

    public void setHasDesktopLicense(boolean hasDesktopLicense) {
        this.hasDesktopLicense = hasDesktopLicense;
    }
}