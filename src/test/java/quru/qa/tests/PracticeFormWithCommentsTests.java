package quru.qa.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormWithCommentsTests extends TestBase {

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
//        $("#gender-radio-2").parent().click();
//        $("[for=gender-radio-2]").click();

        //Mobile
        $("#userNumber").setValue(mobileNumber);

        //Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("August");
//        $(".react-datepicker__month-select").selectOption("August");
        $(".react-datepicker__year-select").selectOptionContainingText("1992");
//        $(".react-datepicker__year-select").selectOption("1992");
//        $("[aria-label=\"Choose Tuesday, August 11th, 1992\"]").click();//лучше не искать так
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
//<div class="react-datepicker__day react-datepicker__day--030 react-datepicker__day--outside-month" tabindex="-1" aria-label="Choose Thursday, July 30th, 1992" role="option" aria-disabled="false">30</div>
//<div class="react-datepicker__day react-datepicker__day--030 react-datepicker__day--weekend" tabindex="-1" aria-label="Choose Sunday, August 30th, 1992" role="option" aria-disabled="false">30</div>

        //Subjects
        $("#subjectsInput").sendKeys(subjects);
        $("#subjectsInput").pressEnter();

        //Hobbies
        $("#hobbiesWrapper").$(byText(hobbies)).click();

        //Picture
//        $("#uploadPicture").uploadFile(new File("src/test/resources/img/1.png"));
        $("#uploadPicture").uploadFromClasspath("img/1.png");

        //Current Address
        $("#currentAddress").setValue(address);

        //State
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();

        //City
        $("#city").scrollTo().click();
        $("#stateCity-wrapper").$(byText("Panipat")).click();

        //Клик Submit
        $("#submit").click();

        //Проверка вывода формы подтверждения
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
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
//        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(birthday));
        checkTable("Student Name", name + " " + lastName);

        //Клик Close
        $("#closeLargeModal").click();
    }
    void checkTable (String key, String value) {
        $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));
    }
}
