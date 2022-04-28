package com.example.soap2day2.controller.dao.popularMovies;

import com.example.soap2day2.entity.PopularMovies;

import java.net.MalformedURLException;
import java.util.List;

public interface PopularMoviesDAO<T> {
     List<PopularMovies> getAllPopularMovies();
     void saveAllPopularMovies(String tableToDelete, String xPath, Object obj) throws MalformedURLException;

     void deleteAllPopularMovies(String myTable);
}
