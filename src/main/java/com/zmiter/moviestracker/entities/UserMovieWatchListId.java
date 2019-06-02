package com.zmiter.moviestracker.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserMovieWatchListId implements Serializable {

    private static final long serialVersionUID = -1113163594794085313L;

    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "movie_id", nullable = false)
    private Long movieId;

    public UserMovieWatchListId(Long userId, Long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof UserMovieWatchListId)) {
            return false;
        }
        UserMovieWatchListId castOther = (UserMovieWatchListId) other;

        return ((this.getMovieId() == null ? castOther.getMovieId() == null : this.getMovieId().equals(castOther.getMovieId())) || (this.getMovieId() != null && castOther.getMovieId() != null && this.getMovieId().equals(castOther.getMovieId())))
                && ((this.getUserId() == null ? castOther.getUserId() == null : this.getUserId().equals(castOther.getUserId())) || (this.getUserId() != null && castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())));
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 37 * result + (getMovieId() == null ? 0 : this.getMovieId().hashCode());
        result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
        return result;
    }
}
