package com.example.soap2day2.controller.dao.newMovies;


import com.example.soap2day2.controller.dao.popularMovies.PopularMoviesIMPL;
import com.example.soap2day2.entity.NewMovies;
import com.example.soap2day2.entity.PopularMovies;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Repository
public class NewMoviesIMPL implements NewMoviesDAO{
    EntityManager entityManager;
    PopularMoviesIMPL popularMoviesIMPL;

    @Override
    @Transactional
    public List<NewMovies> getAllPopularMovies() {
        Session currSess = entityManager.unwrap(Session.class);
        Query<NewMovies> myQuery = currSess.createQuery("from NewMovies");
        return myQuery.getResultList();
    }

    @Override
    @Transactional
    public void saveAllPopularMovies(String tableToDelete,String xPath, Object movie) throws MalformedURLException {
        Object newMovies = new NewMovies();
        popularMoviesIMPL.saveAllPopularMovies("NewMovies", "(//div[contains(@id, \"WHAT_TO_X_CLUSTER_RESULT_GROUP_\")])[3]", newMovies);
    }

}
