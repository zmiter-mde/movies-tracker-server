package com.zmiter.moviestracker.controller;

import com.zmiter.moviestracker.dtos.MovieDto;
import com.zmiter.moviestracker.security.UserPrincipal;
import com.zmiter.moviestracker.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @GetMapping("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDto> getMovies(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {

        return moviesService.getMovies(page, size);
    }

    @GetMapping("/my-watch-list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDto> getUserWatchList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {

        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return moviesService.getUserWatchList(user.getId() ,page, size);
    }

    @GetMapping("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDto> searchMovies(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "title") String title) {

        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return moviesService.search(title, page, size);
    }

}
