package com.example.soap2day2.service.PopularMovieService;

import com.example.soap2day2.entity.PopularMovies;

import java.net.MalformedURLException;
import java.util.List;

public interface PopularMovieService {

    List<PopularMovies> getAllPopularMovies();
    void saveAllPopularMovies() throws MalformedURLException;
}
