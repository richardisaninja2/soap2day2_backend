package com.example.soap2day2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "new_movies")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class NewMovies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private String id;

    @Column(name="Title")
    private String Title;

    @Column(name = "Year")
    private String Year;

    @Column(name = "Rated")
    private String Rated;

    @Column(name = "Released")
    private String Released;

    @Column(name = "Runtime")
    private String Runtime;

    @Column(name = "Genre")
    private String Genre;

    @Column(name = "Director")
    private String Director;

    @Column(name = "Writer")
    private String Writer;

    @Column(name = "Actors")
    private String Actors;

    @Column(name = "Plot")
    private String Plot;

    @Column(name = "Language")
    private String Language;

    @Column(name = "Country")
    private String Country;

    @Column(name = "Awards")
    private String Awards;

    @Column(name = "Poster")
    private String Poster;

    @Column(name = "Metascore")
    private String Metascore;

    @Column(name = "imdb_rating")
    private String imdbRating;

    @Column(name = "imdb_votes")
    private String imdbVotes;

    @Column(name = "imdb_id")
    private String imdbId;

    @Column(name = "Type")
    private String Type;

    @Column(name = "DVD")
    private String DVD;

    @Column(name = "box_office")
    private String BoxOffice;

    @Column(name = "Production")
    private String Production;

    @Column(name = "Website")
    private String Website;

    @Column(name = "Response")
    private String Response;
}
