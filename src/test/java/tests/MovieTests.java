package tests;

import common.BaseTest;
import models.MovieModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MoviePage;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class MovieTests extends BaseTest {

    private static MoviePage moviePage;

    @BeforeMethod
    public void setup(){
        //código do login
    }

    @Test
    public void shouldRegisterANEWMovie(){
        MovieModel movieData = new MovieModel(
                "Jumanji - Próxima fase",
                "Pré-venda",
                2020,
                "16/01/2020",
                Arrays.asList("The Rock", "Jack Black", "Kevin Hard", "Karen Gillan", "Danny DeVito"),
                "Tentado a revisitar o mundo de Jumanji, Spencer decide consertar o bug no jogo do game.",
                "jumanji2.jpg"
        );

        moviePage.add()
                .create(movieData)
                .items().findBy(text(movieData.title)).shouldBe(visible);
    }

    @Test
    public void shouldSearchOneMovie(){
        moviePage.search("Bad").items().shouldHaveSize(1);
    }
}
