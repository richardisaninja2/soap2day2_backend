package com.example.soap2day2.controller.dao.popularMovies;

import com.example.soap2day2.entity.PopularMovies;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;

@AllArgsConstructor
@Repository
public class PopularMoviesIMPL<T> implements PopularMoviesDAO{
    private final EntityManager entityManager;

    @Transactional
    @Override
    public List<PopularMovies> getAllPopularMovies() {
        Session currSess = entityManager.unwrap(Session.class);
        Query<PopularMovies> myQuery = currSess.createQuery("from PopularMovies");
        return myQuery.getResultList();
    }

    public WebDriver getDriver(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Murra\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        Map<String, String> movieMap = new HashMap<>();
        WebDriver driver;

        var options = new ChromeOptions();

        options.addArguments("--disable-blink-features");
        options.addArguments("--disable-blink-features=AutomationControlled");

        driver = new ChromeDriver(options);
        return driver;
    }
    public Map<String, List<String>> getHyperLinks(String startingXPath) {
        WebDriver driver = getDriver();
        driver.navigate().to("https://google.com");
        driver.manage().window().maximize();

        // identify element
        WebElement p=driver.findElement(By.name("q"));

        //enter text with sendKeys() then apply submit()
        p.sendKeys("movies on netflix");
        p.submit();

        List<String> popularMovieNames = new ArrayList<>();
        List<String> popularMovieYear = new ArrayList<>();
        Map<String, List<String>> returnMap = new HashMap<>();
        List<String> popularMovieRating = new ArrayList<>();
        List<String> adjustedPopularMovieYear = new ArrayList<>();
        //close the sign up pop up if it shows
        if(driver.findElements(By.xpath("(//span[@class = 'z1asCe wuXmqc'])[2]")).size() > 0){
            driver.findElement(By.xpath("(//span[@class = 'z1asCe wuXmqc'])[2]")).click();
        }
        //Wait until the google page shows the result
        WebElement myDynamicElement = (new WebDriverWait(driver, Duration.ofSeconds(5))).until(ExpectedConditions.presenceOfElementLocated(By.xpath(startingXPath + "//g-right-button//g-fab")));
        //click arrow to expand screen

        driver.findElement(By.xpath("(//div[@class = 'FAl3Ic'])[2]")).click();

//            //click on the arrow to go right
        driver.findElement(By.xpath(startingXPath + "//g-right-button//g-fab")).click();

        while(driver.findElements(By.xpath(startingXPath + "//div[@class ='iY0Hed']//a[@class= 'oZ5mO']")).size() < 9){
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
        //get all the moviee names

        List<WebElement> popularMovies = driver.findElements(By.xpath(startingXPath + "//div[@class ='iY0Hed']//a[@class= 'oZ5mO']"));

        for(var e : popularMovies){
            popularMovieNames.add(e.getAttribute("innerHTML"));
        }

//            List<WebElement> popularMoviesYearElement = driver.findElements(By.xpath(startingXPath + "//div[@class =\"iY0Hed\"]//div[@class = \"i5JHkf ellip\"]//div[4]"));
//
//            List<WebElement> popularMoviesYearElement1 = driver.findElements(By.xpath(startingXPath + "//div[@class =\"iY0Hed\"]//div[@class = \"i5JHkf ellip\"]//div[1]"));
//            for(var e: popularMoviesYearElement){
//                popularMovieYear.add(e.getAttribute("innerHTML"));
//            }
//        for(var e: popularMoviesYearElement1){
//            popularMovieRating.add(e.getAttribute("innerHTML"));
//        }
//        for(int i =0; i<popularMovieYear.size();i++){
//            if(!popularMovieRating.get(i).toUpperCase().startsWith("R") && !popularMovieRating.get(i).startsWith("N") && !popularMovieRating.get(i).startsWith("G") && !popularMovieRating.get(i).startsWith("PG") && !popularMovieRating.get(i).contains("h ")){
//                adjustedPopularMovieYear.add("0");
//                adjustedPopularMovieYear.add(popularMovieYear.get(i));
//            }else{
//                adjustedPopularMovieYear.add(popularMovieYear.get(i));
//            }
//        }

        System.out.println(popularMovieNames);
        System.out.println(popularMovieNames.size());
        System.out.println(adjustedPopularMovieYear);
        System.out.println(adjustedPopularMovieYear.size());
        System.out.println(popularMovieRating);
        System.out.println(popularMovieRating.size());
        driver.close();
        returnMap.put("popularMovies", popularMovieNames);
        returnMap.put("popularMovieYear", adjustedPopularMovieYear);

        return returnMap;
    }

    public void deleteAllPopularMovies(String myTable){
        String hql = String.format("delete from %s",myTable);
        Session currSess = entityManager.unwrap(Session.class);
        Query query = currSess.createQuery(hql);
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void saveAllPopularMovies(String tableToDelete, String xPath, Object movie) throws MalformedURLException {
    //delete all entries in table popularMovies
    deleteAllPopularMovies(tableToDelete);
    Map<String, List<String>> movieMap = getHyperLinks(xPath);
    List <String> popularMovies = movieMap.get("popularMovies");
    List <String> popularMovieYear = movieMap.get("popularMovieYear");

    PopularMovies popularMoviesEntity = new PopularMovies();

        Gson gson = new Gson();
        //get current year for the for loop
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for(int i = 0; i<popularMovies.size(); i++){
            for(int y = year; y>=2010; y--){

                try (InputStream inputStream = new URL("http://www.omdbapi.com/?apikey=a81836fb&t=" +popularMovies.get(i).replace(' ', '+')+"&y="+y).openStream()) {

                    String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                    // Convert JSON File to Java Object

                     movie = gson.fromJson(json, movie.getClass());
                     Object value = null;
                        Field[] arr = movie.getClass().getDeclaredFields();
                        for(var e: arr){
                            e.setAccessible(true);
                            value = e.get(movie);
                        }
                     if(value.equals("True")){
                         entityManager.merge(movie);
                         if(i % popularMovies.size() == 0) {
                             entityManager.flush();
                             entityManager.clear();
                         }
                         break;
                     }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        entityManager.flush();
        entityManager.clear();

    }
}
