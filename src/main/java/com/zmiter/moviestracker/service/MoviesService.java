package com.zmiter.moviestracker.service;

import com.zmiter.moviestracker.dao.MoviesDao;
import com.zmiter.moviestracker.dtos.MovieDto;
import com.zmiter.moviestracker.entities.Movie;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoviesService {

    @Autowired
    private MoviesDao moviesDao;

    public List<MovieDto> getMovies(int page, int size) {

        Pageable pageRequest = PageRequest.of(page, size);

        return moviesDao.findByOrderByReleaseDateAsc(pageRequest)
                .stream()
                .map(MoviesService::fromMovieEntity)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getUserWatchList(Long userId, int page, int size) {

        Pageable pageRequest = PageRequest.of(page, size);

        return moviesDao.findMoviesFromUserWatchList(userId, pageRequest)
                .stream()
                .map(MoviesService::fromMovieEntity)
                .collect(Collectors.toList());
    }

    public List<MovieDto> search(String title, int page, int size) {

        Pageable pageRequest = PageRequest.of(page, size);

        return moviesDao.findMoviesByTitleEnIgnoreCaseContainingOrTitleRuIgnoreCaseContaining(title, title, pageRequest)
                .stream()
                .map(MoviesService::fromMovieEntity)
                .collect(Collectors.toList());
    }

    private static MovieDto fromMovieEntity(Movie movie) {
        MovieDto movieDto = new MovieDto();
        BeanUtils.copyProperties(movie, movieDto);
        return movieDto;
    }
}
