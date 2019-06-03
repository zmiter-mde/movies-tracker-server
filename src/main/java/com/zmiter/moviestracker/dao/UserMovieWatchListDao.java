package com.zmiter.moviestracker.dao;

import com.zmiter.moviestracker.entities.UserMovieWatchList;
import com.zmiter.moviestracker.entities.UserMovieWatchListId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserMovieWatchListDao extends JpaRepository<UserMovieWatchList, UserMovieWatchListId> {

    Optional<UserMovieWatchList> findByUserIdAndMovieId(@Param("userId") Long userId, @Param("movieId") Long movieId);

    @Query(value = "SELECT umwl.movie_id FROM user_movie_watch_list umwl WHERE umwl.user_id = :userId", nativeQuery = true)
    Set<Long> getIds(@Param("userId") Long userId);
}
