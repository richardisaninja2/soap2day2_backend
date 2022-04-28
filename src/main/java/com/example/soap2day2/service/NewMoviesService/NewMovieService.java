package com.example.soap2day2.service.NewMoviesService;

import com.example.soap2day2.entity.NewMovies;

import java.net.MalformedURLException;
import java.util.List;

public interface NewMovieService {
    List<NewMovies> getAllPopularMovies();
    void saveAllPopularMovies() throws MalformedURLException;

}
