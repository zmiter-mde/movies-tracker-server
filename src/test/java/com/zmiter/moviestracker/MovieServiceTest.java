package com.zmiter.moviestracker;

import com.zmiter.moviestracker.dtos.MovieDto;
import com.zmiter.moviestracker.enumeration.MovieCategory;
import com.zmiter.moviestracker.service.MoviesService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {

    @Autowired
    private MoviesService moviesService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<MovieDto> movies = moviesService.getMovies(0, 10, MovieCategory.ALL);

        Assert.assertTrue(movies.size() > 0);
    }
}
