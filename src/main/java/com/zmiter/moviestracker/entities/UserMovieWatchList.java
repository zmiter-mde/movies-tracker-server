package com.zmiter.moviestracker.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_movie_watch_list")
public class UserMovieWatchList {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "movieId", column = @Column(name = "movie_id", nullable = false)),
            @AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false))})
    private UserMovieWatchListId id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false, insertable = false, updatable = false)
    private Movie movie;

    public UserMovieWatchList() {}

    public UserMovieWatchList(Movie movie, User user) {
        this.id = new UserMovieWatchListId(movie.getId(), user.getId());
        this.movie = movie;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
