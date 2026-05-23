package dev.gustavoquin.integrador.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.gustavoquin.integrador.model.constant.NewsStatus;

import java.time.LocalDateTime;

public class NewsDTO {

    private Long id;

    @JsonProperty("titulo")
    private String title;

    @JsonProperty("resumen")
    private String summary;

    @JsonProperty("contenido")
    private String content;

    @JsonProperty("imagenURL")
    private String imageURL;

    @JsonProperty("estatus")
    private NewsStatus status;

    @JsonProperty("publicado en")
    private LocalDateTime publishedAt;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getSummary() {

        return summary;
    }

    public void setSummary(String summary) {

        this.summary = summary;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getImageURL() {

        return imageURL;
    }

    public void setImageURL(String imageURL) {

        this.imageURL = imageURL;
    }

    public NewsStatus getStatus() {

        return status;
    }

    public void setStatus(NewsStatus status) {

        this.status = status;
    }

    public LocalDateTime getPublishedAt() {

        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {

        this.publishedAt = publishedAt;
    }
}
