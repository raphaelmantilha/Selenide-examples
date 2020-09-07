package tests;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.isChrome;

import static com.codeborne.selenide.Condition.text;

public class LoginTestsSelenide {

    @Test
    public void shouldSeeLoggedUser(){
        isChrome();
        open("http://ninjaplus-web:5000");

        $("#emailId").setValue("papito@ninjaplus.com");
        $("#passId").setValue("pwd123");
        $(byText("Entrar")).click();

        $(".user .info span").shouldHave(text("Papito"));

    }
}
