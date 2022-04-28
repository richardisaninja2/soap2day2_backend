package com.example.soap2day2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;

@Entity
@Table(name="movies")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "movie_date")
    private String movieDate;

    @Column(name="movie_link")
    private String movieLink;
}
