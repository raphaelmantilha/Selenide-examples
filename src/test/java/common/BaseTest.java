package common;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.MoviePage;
import pages.SideBar;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import static com.codeborne.selenide.Selenide.screenshot;

public class BaseTest {

    protected static LoginPage loginPage;
    protected static MoviePage moviePage;
    protected static SideBar sideBar;

    @BeforeMethod
    public void start() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://192.168.99.100:5000";
        Configuration.timeout = 30000;

        loginPage = new LoginPage();
        sideBar = new SideBar();
        moviePage = new MoviePage();
    }

    @AfterMethod
    public void finish(){
        // Tiramos um screenshot pelo Selenide
        String tempShot = screenshot("temp_shot");

        // Queremos transformar em binário para anexar no report do Allure
        try{
            BufferedImage bimage = ImageIO.read(new File(tempShot));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bimage, "png", baos);
            byte [] finalShot = baos.toByteArray();
            io.qameta.allure.Allure.addAttachment("Evidência", new ByteArrayInputStream(finalShot));
        }catch (Exception ex){
            System.out.println("Deu erro ao anexar o Screenshot :(. Trace => " + ex.getMessage());
        }


    }
}
