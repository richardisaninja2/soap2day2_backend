package com.example.soap2day2.controller;

import com.example.soap2day2.entity.PopularMovies;
import com.example.soap2day2.service.PopularMovieService.PopularMovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class PopularMoviesController {
    private final PopularMovieService popularMovieService;


//    @GetMapping("/listPopularMovies")
//        public List<PopularMovies> getAll(){
//        return popularMovieService.getAllPopularMovies();
//    }
//
//    @PostMapping("/retrievePopularMovies")
//    public List<PopularMovies> retrieveMovies() throws MalformedURLException {
//        popularMovieService.saveAllPopularMovies();
//        return popularMovieService.getAllPopularMovies();
//    }
}
