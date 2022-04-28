package com.example.soap2day2.controller.dao;

import com.example.soap2day2.entity.Movies;

import java.util.List;

public interface MoviesDAO {
    List<Movies> getAll();
    Object findByTitle(String title);
    void saveAllMovies();
}
