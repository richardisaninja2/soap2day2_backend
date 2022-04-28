package com.example.soap2day2.service.PopularMovieService;


import com.example.soap2day2.controller.dao.popularMovies.PopularMoviesDAO;
import com.example.soap2day2.entity.PopularMovies;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;
@AllArgsConstructor
@Service
public class PopularMovieServiceIMPL implements PopularMovieService{
    private final PopularMoviesDAO popularMoviesDAO;

    @Override
    public List<PopularMovies> getAllPopularMovies() {
        return popularMoviesDAO.getAllPopularMovies();
    }

    @Override
    public void saveAllPopularMovies() throws MalformedURLException {
        Object PopularMovies = new PopularMovies();
        this.popularMoviesDAO.saveAllPopularMovies("PopularMovies","(//div[contains(@id, \"WHAT_TO_X_CLUSTER_RESULT_GROUP_\")])[1]", PopularMovies);
    }
}
