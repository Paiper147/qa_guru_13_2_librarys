package quru.qa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1519x800";
    }

    @Test
    void successfullTestPracticeForm() {

        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#close-fixedban').remove()");

        String name = "myName";
        String lastName = "myLastName";
        String email = "myEmail@mail.com";
        String gender = "Female";
        String mobileNumber = "0123456789";
        String birthday = "11 August,1992";
        String subjects = "Maths";
        String hobbies = "Reading";
        String picture = "1.png";
        String address = "my currentAddress";
        String stateAndCity = "Haryana Panipat";

        //Name + myLastName + userEmail
        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);

        //Gender
        $("#genterWrapper").$(byText(gender)).click();

        //Mobile
        $("#userNumber").setValue(mobileNumber);

        //Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("August");
        $(".react-datepicker__year-select").selectOptionContainingText("1992");
        $("[aria-label=\"Choose Tuesday, August 11th, 1992\"]").click();

        //Subjects
        //вариант 1
        $("#subjectsInput").sendKeys(subjects);
        $("#subjectsInput").pressEnter();

        //Hobbies
        $("#hobbiesWrapper").$(byText(hobbies)).click();

        //Picture
        File file = new File("src/test/java/quru/qa/tests/resourses/1.png");
        $("#uploadPicture").uploadFile(file);

        //Current Address
        $("#currentAddress").setValue(address);

        //State
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();

        //City
        $("#stateCity-wrapper").$(byText("Panipat")).click();

        //Клик Submit
        $("#submit").click();

        //Проверка вывода формы подтверждения
        $(byText("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(name),
                text(lastName),
                text(email),
                text(gender),
                text(mobileNumber),
                text(birthday),
                text(subjects),
                text(hobbies),
                text(picture),
                text(address),
                text(stateAndCity)
        );

        //Клик Close
        $("#closeLargeModal").click();
    }
}
