package com.zmiter.moviestracker.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "title_ru", length = 512)
    private String titleRu;
    @Column(name = "title_en", nullable = false, length = 512)
    private String titleEn;
    @Column(name = "kinopoisk_link", length = 2048)
    private String kinopoiskLink;
    @Column(name = "imdb_link", length = 2048)
    private String imdbLink;
    @Column(name = "image_url", length = 2048)
    private String imageUrl;
    @Column(name = "release_date", nullable = false, length = 2048)
    private LocalDate releaseDate;
    @Transient
    private boolean isReleased;

    public Movie() {}

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

    public boolean isReleased() {
        return releaseDate != null && LocalDate.now().isAfter(releaseDate);
    }
}
