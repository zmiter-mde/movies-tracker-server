package com.zmiter.moviestracker.dao;

import com.zmiter.moviestracker.entities.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesDao extends JpaRepository<Movie, Long> {

    List<Movie> findByOrderByReleaseDateAsc(Pageable pageRequest);

    @Query("SELECT umwl.movie FROM UserMovieWatchList umwl WHERE umwl.user.id = :userId ORDER BY umwl.movie.releaseDate ASC")
    List<Movie> findMoviesFromUserWatchList(@Param("userId") Long userId, Pageable pageRequest);

    List<Movie> findMoviesByTitleEnIgnoreCaseContainingOrTitleRuIgnoreCaseContainingOrderByReleaseDateAsc(
            @Param("titleEn") String titleEn,
            @Param("titleRu") String titleRu,
            Pageable pageable);
    
}
