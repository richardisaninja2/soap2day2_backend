package com.example.soap2day2.service;

import com.example.soap2day2.controller.dao.MoviesDAO;
import com.example.soap2day2.entity.Movies;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MoviesServiceIMPL implements MoviesService{
    private final MoviesDAO moviesDAO;

    @Override
    public List<Movies> getAll() {
        return moviesDAO.getAll();
    }

    @Override
    public Object findByTitle(String title) {
        return moviesDAO.findByTitle(title);
    }

    @Override
    public void saveAllMovies() {
        moviesDAO.saveAllMovies();
    }
}
