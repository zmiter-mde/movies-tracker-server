package com.zmiter.moviestracker.controller;

import com.zmiter.moviestracker.dtos.MovieDto;
import com.zmiter.moviestracker.enumeration.MovieCategory;
import com.zmiter.moviestracker.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @GetMapping("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDto> getMovies(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "category", defaultValue = "ALL", required = false) MovieCategory category) {

        return moviesService.getMovies(page, size, category);
    }

}
