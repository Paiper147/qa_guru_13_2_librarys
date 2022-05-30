package quru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @BeforeAll
    static void beforeAll(){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1519x800";
    }

    @Test
    void successfullTestPracticeForm() {

        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");

        //Name + myLastName + userEmail
        $("#firstName").setValue("myName");
        $("#lastName").setValue("myLastName");
        $("#userEmail").setValue("myEmail@mail.com");

        //Gender
//        $("#gender-radio-2").selectRadio("Female"); - НЕ работает
        //вариант_1
        $("label[for=gender-radio-2]").click();
        //вариант_2
        //SelenideElement sel = $(By.id("gender-radio-2"));
        //executeJavaScript("arguments[0].click();", sel);

        //Mobile
        $("#userNumber").setValue("0123456789");

        //Date of Birth
        $("#dateOfBirthInput").click();
//        //$("#dateOfBirthInput").setValue("11 Aug 1992"); - НЕ работает
//        //$(".react-datepicker__month-select").selectOption(7); //или selectOptionContainingText("August")
        $(".react-datepicker__month-select").selectOptionContainingText("August");
        $(".react-datepicker__year-select").selectOptionContainingText("1992");
//        $(".react-datepicker__day react-datepicker__day--011").click(); - НЕ находит значение
        $("[aria-label=\"Choose Tuesday, August 11th, 1992\"]").click();
//        $("[aria-label=Choose Tuesday, August 11th, 1992]").click(); //- invalid selector: An invalid or illegal selector was specified
//        $("[label=Choose Tuesday, August 11th, 1992]").click(); - invalid selector: An invalid or illegal selector was specified


        //executeJavaScript(String.format("$('#dateOfBirthInput').val('11 August 1992')")); - НЕ отправляет значение
        //executeJavaScript(String.format("$('%s').datepicker('setDate', '%s')", ".react-datepicker__input-container", "11 August 1992"));- НЕ работает
        //executeJavaScript(String.format("$('%s').datepicker('setDate', '%s')", "#dateOfBirthInput", "11 August 1992"));- НЕ работает
        //executeJavaScript(String.format("$('{0}').datepicker('setDate', '{1}')", "#dateOfBirthInput", "11 August 1992"));- НЕ работает
        sleep(1000);

        //Subjects
//        $("#subjectsInput").setValue("m"); - НЕ работает
        $("#subjectsInput").sendKeys("Maths");
        $("#subjectsInput").pressEnter();

        //Hobbies
        //$("#hobbies-checkbox-2").setSelected(true);
        $("label[for=hobbies-checkbox-2]").click();

        //Picture
        File file = new File("src/test/java/quru/qa/tests/resourses/1.jpg");
        $("#uploadPicture").uploadFile(file);

        //Current Address
        $("#currentAddress").setValue("my currentAddress");

        //State
        //$("#state").click(); - НЕ обязательно, без этого работает
        $("#react-select-3-input").sendKeys("Haryana");
        $("#react-select-3-input").pressEnter();

        //City
        //$("#city").click(); - НЕ обязательно, без этого работает
        $("#react-select-4-input").sendKeys("Panipat");
        $("#react-select-4-input").pressEnter();

        sleep(2000);

        $("#submit").click();

        sleep(2000);


    }
}
