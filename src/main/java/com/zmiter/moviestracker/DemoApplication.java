package com.zmiter.moviestracker;

import com.zmiter.moviestracker.dtos.MovieDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@Configuration
public class DemoApplication {

	@GetMapping("/movies")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MovieDto> getMovies() {
		List<MovieDto> movies = new ArrayList<>(2);
		MovieDto movie1 = new MovieDto();
		movie1.setId(1L);
		movie1.setTitleEn("My Spy");
		movie1.setTitleRu("Мой шпион");
		movie1.setImdbLink("https://www.imdb.com/title/tt8242084");
		movie1.setKinopoiskLink("https://www.kinopoisk.ru/film/1179073");
		movie1.setImageUrl("https://www.kinopoisk.ru/images/film_big/1179073.jpg");
		movie1.setReleaseDate(LocalDate.of(2019, 8, 23));

		movies.add(movie1);

		MovieDto movie2 = new MovieDto();
		movie2.setId(2L);
		movie2.setTitleEn("Once Upon a Time ... in Hollywood");
		movie2.setTitleRu("Однажды... в Голливуде");
		movie2.setImdbLink("https://www.imdb.com/title/tt7131622");
		movie2.setKinopoiskLink("https://www.kinopoisk.ru/film/odnazhdy-v-gollivude-2019-1047883");
		movie2.setImageUrl("https://m.media-amazon.com/images/M/MV5BMTYxNjE1NzE4Nl5BMl5BanBnXkFtZTgwNDkzNjc2NzM@._V1_SY1000_SX675_AL_.jpg");
		movie2.setReleaseDate(LocalDate.of(2019, 7, 29));

		movies.add(movie2);

		return movies;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/movies").allowedOrigins("http://localhost:8080");
			}
		};
	}

}
