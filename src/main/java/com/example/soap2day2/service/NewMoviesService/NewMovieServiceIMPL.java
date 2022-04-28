package com.example.soap2day2.service.NewMoviesService;

import com.example.soap2day2.controller.dao.newMovies.NewMoviesDAO;
import com.example.soap2day2.entity.NewMovies;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;

@Service
@AllArgsConstructor
public class NewMovieServiceIMPL implements NewMovieService{
    private final NewMoviesDAO newMoviesDAO;

    @Override
    public List<NewMovies> getAllPopularMovies() {
       return newMoviesDAO.getAllPopularMovies();
    }

    @Override
    public void saveAllPopularMovies() throws MalformedURLException {
        Object newMovies = new NewMovies();
        newMoviesDAO.saveAllPopularMovies("NewMovies", "(//div[contains(@id, \"WHAT_TO_X_CLUSTER_RESULT_GROUP_\")])[3]", newMovies);
    }
}
