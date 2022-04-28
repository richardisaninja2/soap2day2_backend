package com.example.soap2day2.controller;

import com.example.soap2day2.entity.Movies;
import com.example.soap2day2.entity.NewMovies;
import com.example.soap2day2.entity.PopularMovies;
import com.example.soap2day2.service.MoviesService;
import com.example.soap2day2.service.NewMoviesService.NewMovieService;
import com.example.soap2day2.service.PopularMovieService.PopularMovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@AllArgsConstructor
public class MoviesController {
    private final MoviesService moviesService;
    private final PopularMovieService popularMovieService;
    private final NewMovieService newMovieService;

    @GetMapping("/getAll")
    public List getAll(){
       return moviesService.getAll();
    }

    @GetMapping("/findByName/{name}")
    public Object findById(@PathVariable String name){
        return moviesService.findByTitle(name);
    }

    //get popular movies from database
    @GetMapping("/listPopularMovies")
    public List<PopularMovies> getAllPopularMovies(){
        return popularMovieService.getAllPopularMovies();
    }

    //get all movies from soap2day and save it to the database
    @PostMapping("/addMovies")
    public List<Movies> addMovies(){
        moviesService.saveAllMovies();
        return moviesService.getAll();
    }
    //get popular movies from google and save it to the database
    @PostMapping("/retrievePopularMovies")
    public List<PopularMovies> retrieveMovies() throws MalformedURLException {
        popularMovieService.saveAllPopularMovies();
        return popularMovieService.getAllPopularMovies();
    }
    @GetMapping("/getNewMovies")
    public List<NewMovies> getNewMoviesFromDatabase(){
        return newMovieService.getAllPopularMovies();
    }

    @PostMapping("/addNewMovies")
    public List<NewMovies> getNewMoviesFromGoogle() throws MalformedURLException {
        newMovieService.saveAllPopularMovies();
        return getNewMoviesFromDatabase();
    }

}
