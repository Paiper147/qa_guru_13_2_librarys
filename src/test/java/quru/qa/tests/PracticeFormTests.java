package quru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests extends TestBase {

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
        String birthday = "30 August,1992";
        String dayBirthday = "30";
        String monthBirthday = "August";
        String yearBirthday = "1992";
        String subjects = "Maths";
        String hobbies = "Reading";
        String picture = "1.png";
        String picturePath = "img/1.png";
        String address = "my currentAddress";
        String state = "Haryana";
        String city = "Panipat";
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
        $(".react-datepicker__month-select").selectOption(monthBirthday);
        $(".react-datepicker__year-select").selectOption(yearBirthday);
        $(".react-datepicker__day--0" + dayBirthday + ":not(.react-datepicker__day--outside-month)").click();

        //Subjects
        $("#subjectsInput").sendKeys(subjects);
        $("#subjectsInput").pressEnter();

        //Hobbies
        $("#hobbiesWrapper").$(byText(hobbies)).click();

        //Picture
        $("#uploadPicture").uploadFromClasspath(picturePath);

        //Current Address
        $("#currentAddress").setValue(address);

        //State
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();

        //City
        $("#city").scrollTo().click();
        $("#stateCity-wrapper").$(byText(city)).click();

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
