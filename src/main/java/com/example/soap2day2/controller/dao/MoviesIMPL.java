package com.example.soap2day2.controller.dao;

import com.example.soap2day2.entity.Movies;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;

@AllArgsConstructor
@Repository
public class MoviesIMPL implements MoviesDAO{
    private final EntityManager entityManager;

    @Override
    @Transactional
    public List<Movies> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Movies> myQuery = currentSession.createQuery("from Movies");
        return myQuery.getResultList();
    }

    @Override
    @Transactional
    public Object findByTitle(String title) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Session.class, title);
    }

    @Override
    @Transactional
    public void saveAllMovies() {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\Murra\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        Map<String, String> movieMap = new HashMap<>();
        WebDriver driver;


        var options = new ChromeOptions();

        options.addArguments("--disable-blink-features");
        options.addArguments("--disable-blink-features=AutomationControlled");

        driver = new ChromeDriver(options);

        driver.get("https://soap2day.ac/movielist/");

        driver.findElement(By.id("btnhome")).click();

        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));

        WebElement lastPagination = driver.findElement(By.xpath("//div[@class = 'col-sm-12 col-lg-12 text-center']/ul/li[last()-1]/a"));
        int numberOfPages = Integer.parseInt(lastPagination.getAttribute("innerHTML"));
        System.out.println(numberOfPages);

        Map<String, String> map = new LinkedHashMap<>();
        ArrayList<String> movieName = new ArrayList<>();
        ArrayList<String> movieDate = new ArrayList<>();
        ArrayList<String> moviePageLink = new ArrayList<>();
        ArrayList<String> actualMovieLink = new ArrayList<>();

        int number = 0;
        for(int i = 1; i<=1; i++){

            driver.get("https://soap2day.sh/movielist?page="+i);
            if(driver.findElements(By.id("btnhome")).size() > 0){
                driver.findElement(By.id("btnhome")).click();
            }

            List<WebElement> moviePosterLinks = driver.findElements(By.xpath("//div[@class = 'thumbnail text-center']/div/h5/a"));
            List<WebElement> moviePosterDate = driver.findElements(By.xpath("//div[@class = 'img-tip label label-info']"));
            for(WebElement e: moviePosterDate){
                movieDate.add(e.getAttribute("innerHTML"));
            }
            for(WebElement e: moviePosterLinks){
                movieName.add(e.getAttribute("innerHTML"));
                moviePageLink.add(e.getAttribute("href"));
            }
        }
        for(int i = 0; i<moviePageLink.size(); i++){
            driver.get(moviePageLink.get(i));
            if(driver.findElements(By.id("btnhome")).size() > 0){
                driver.findElement(By.id("btnhome")).click();
            }
            List<WebElement> movieLink = driver.findElements(By.xpath("//video[@class = 'jw-video jw-reset']"));
            for(WebElement e: movieLink){
                actualMovieLink.add(e.getAttribute("src"));
            }
        }

        Movies movies = new Movies();

//        Session currentSession = entityManager.unwrap(Session.class);
//        for(int i = 0; i < moviePageLink.size(); i++){
//
//            currentSession.saveOrUpdate(movies); // save the current transaction
//        }
        for (int i = 0; i < moviePageLink.size(); i++) {
            movies.setMovieDate(movieDate.get(i).split("-")[0]);
            movies.setTitle(movieName.get(i));
            movies.setMovieLink(actualMovieLink.get(i));
            entityManager.merge(movies);
            if(i % moviePageLink.size() == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }

}
