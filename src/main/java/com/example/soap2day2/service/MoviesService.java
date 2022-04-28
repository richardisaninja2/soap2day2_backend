package com.example.soap2day2.service;

import com.example.soap2day2.entity.Movies;

import java.util.List;

public interface MoviesService {
    List<Movies> getAll();
    Object findByTitle(String title);
    void saveAllMovies();
}
