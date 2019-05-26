package com.zmiter.moviestracker.dtos;

import java.time.LocalDate;

public class MovieDto {

    private long id;
    private String titleRu;
    private String titleEn;
    private String kinopoiskLink;
    private String imdbLink;
    private String imageUrl;
    private LocalDate releaseDate;

    public MovieDto() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getKinopoiskLink() {
        return kinopoiskLink;
    }

    public void setKinopoiskLink(String kinopoiskLink) {
        this.kinopoiskLink = kinopoiskLink;
    }

    public String getImdbLink() {
        return imdbLink;
    }

    public void setImdbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
