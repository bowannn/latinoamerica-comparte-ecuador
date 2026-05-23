package dev.gustavoquin.integrador.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestimonyDTO {

    private Long id;
    @JsonProperty("nombre")
    private String name;
    @JsonProperty("cargo")
    private String role;
    @JsonProperty("contenido")
    private String content;
    @JsonProperty("fotoUrl")
    private String photoUrl;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) { this.role = role; }

    public String getContent() {

        return content;
    }

    public void setContent(String content) { this.content = content; }

    public String getPhotoUrl() { return photoUrl; }

    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
}