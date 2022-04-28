package com.example.soap2day2.controller.dao.newMovies;

import com.example.soap2day2.entity.NewMovies;

import java.net.MalformedURLException;
import java.util.List;

public interface NewMoviesDAO {
    List<NewMovies> getAllPopularMovies();
    void saveAllPopularMovies(String tableToDelete,String xPath, Object obj) throws MalformedURLException;


}
