package com.zmiter.moviestracker.service;

import com.zmiter.moviestracker.dao.MoviesDao;
import com.zmiter.moviestracker.dtos.MovieDto;
import com.zmiter.moviestracker.enumeration.MovieCategory;
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

    public List<MovieDto> getMovies(int page, int size, MovieCategory category) {

        Pageable pageRequest = PageRequest.of(page, size);

        return moviesDao.findByOrderByReleaseDateAsc(pageRequest)
                .stream().map(movie -> {
            MovieDto movieDto = new MovieDto();
            BeanUtils.copyProperties(movie, movieDto);
            return movieDto;
        }).collect(Collectors.toList());
    }
}
