package com.zmiter.moviestracker.dao;

import com.zmiter.moviestracker.entities.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesDao extends JpaRepository<Movie, Long> {

    List<Movie> findByOrderByReleaseDateAsc(Pageable pageRequest);
    
}
