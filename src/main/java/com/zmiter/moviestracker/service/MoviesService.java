package com.zmiter.moviestracker.service;

import com.zmiter.moviestracker.dao.MoviesDao;
import com.zmiter.moviestracker.dao.UserMovieWatchListDao;
import com.zmiter.moviestracker.dao.UsersDao;
import com.zmiter.moviestracker.dtos.MovieDto;
import com.zmiter.moviestracker.entities.Movie;
import com.zmiter.moviestracker.entities.User;
import com.zmiter.moviestracker.entities.UserMovieWatchList;
import com.zmiter.moviestracker.entities.UserMovieWatchListId;
import com.zmiter.moviestracker.exception.common.DuplicateEntryException;
import com.zmiter.moviestracker.exception.common.ResourceNotFoundException;
import com.zmiter.moviestracker.security.UserPrincipal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MoviesService {

    @Autowired
    private MoviesDao moviesDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private UserMovieWatchListDao userMovieWatchListDao;

    public List<MovieDto> getMovies(UserPrincipal userPrincipal, int page, int size) {

        Pageable pageRequest = PageRequest.of(page, size);

        Set<Long> userWatchListMoviesIds = getUserWatchListMoviesIds(userPrincipal);

        return moviesDao.findByOrderByReleaseDateAsc(pageRequest)
                .stream()
                .map(movie -> fromMovieEntity(movie, userWatchListMoviesIds))
                .collect(Collectors.toList());
    }

    public List<MovieDto> getUserWatchList(UserPrincipal userPrincipal, int page, int size) {

        Pageable pageRequest = PageRequest.of(page, size);

        return moviesDao.findMoviesFromUserWatchList(userPrincipal.getId(), pageRequest)
                .stream()
                .map(movie -> fromMovieEntity(movie, true))
                .collect(Collectors.toList());
    }

    public List<MovieDto> search(UserPrincipal userPrincipal, String title, int page, int size) {

        Pageable pageRequest = PageRequest.of(page, size);

        Set<Long> userWatchListMoviesIds = getUserWatchListMoviesIds(userPrincipal);

        return moviesDao.findMoviesByTitleEnIgnoreCaseContainingOrTitleRuIgnoreCaseContainingOrderByReleaseDateAsc(title, title, pageRequest)
                .stream()
                .map(movie -> fromMovieEntity(movie, userWatchListMoviesIds))
                .collect(Collectors.toList());
    }

    public Set<Long> getUserWatchListMoviesIds(UserPrincipal userPrincipal) {

        return userMovieWatchListDao.getIds(userPrincipal.getId());
    }

    private static MovieDto fromMovieEntity(Movie movie, boolean wannaWatch) {
        MovieDto movieDto = fromMovieEntity(movie);
        movieDto.setWannaWatch(wannaWatch);
        return movieDto;
    }

    private static MovieDto fromMovieEntity(Movie movie, Set<Long> userWatchListMoviesIds) {
        MovieDto movieDto = fromMovieEntity(movie);
        movieDto.setWannaWatch(userWatchListMoviesIds.contains(movie.getId()));
        return movieDto;
    }

    private static MovieDto fromMovieEntity(Movie movie) {
        MovieDto movieDto = new MovieDto();
        BeanUtils.copyProperties(movie, movieDto);
        return movieDto;
    }

    public MovieDto addToWatchList(UserPrincipal user, Long movieId) throws DuplicateEntryException {

        Movie movie = getMovieByIdOrException(movieId);

        boolean isMovieInUserWatchList = isMovieInUserWatchList(user.getId(), movieId);

        if (isMovieInUserWatchList) {
            String errMessage = String.format("UserMovieWatchList with userId = [%s] and movieId = [%s] already exists",
                    user.getId(), movieId);
            throw new DuplicateEntryException("UserMovieWatchList", errMessage);
        }

        User userEntity = usersDao.getOne(user.getId());

        UserMovieWatchList userExpectedMovie = new UserMovieWatchList(movie, userEntity);

        userMovieWatchListDao.save(userExpectedMovie);

        return fromMovieEntity(movie, true);
    }

    public MovieDto removeFromWatchList(UserPrincipal user, Long movieId) {

        Movie movie = getMovieByIdOrException(movieId);

        boolean isMovieInUserWatchList = isMovieInUserWatchList(user.getId(), movieId);

        if (!isMovieInUserWatchList) {
            String errMessage = String.format("UserMovieWatchList with userId = [%s] and movieId = [%s] doesn't exist",
                    user.getId(), movie.getId());
            throw new ResourceNotFoundException("UserMovieWatchList", errMessage);
        }

        userMovieWatchListDao.deleteById(new UserMovieWatchListId(movieId, user.getId()));

        return fromMovieEntity(movie, false);
    }

    private boolean isMovieInUserWatchList(Long userId, Long movieId) {

        Optional<UserMovieWatchList> existingUserMovie = userMovieWatchListDao.findByUserIdAndMovieId(userId, movieId);

        return existingUserMovie.isPresent();
    }

    private Movie getMovieByIdOrException(Long movieId) {

        Optional<Movie> movie = moviesDao.findById(movieId);

        if (!movie.isPresent()) {
            throw new ResourceNotFoundException("Movie", "id", movieId);
        }

        return movie.get();
    }
}
