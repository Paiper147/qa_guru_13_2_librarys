package quru.qa.tests;

import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static utils.RandomUtils.*;

public class PracticeFormWithTestDataTests extends TestBase {

    @Test
    void successfulTestPracticeForm() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#close-fixedban').remove()");

        //Name + myLastName + userEmail
        String name = TestData.NAME;
        String lastName = TestData.LAST_NAME;
        String email = getRandomEmail();
        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);

        //Gender
        String gender = getRandomFromInputArray(TestData.INPUT_ARRAY_GENDERS);
        $("#genterWrapper").$(byText(gender)).click();

        //Mobile
        String mobileNumber = getRandomNumberDesiredSize(10);
        $("#userNumber").setValue(mobileNumber);

        //Date of Birth
        LocalDate birthdayLocalDate = getRandomBirthday();
        int dayBirthday = birthdayLocalDate.getDayOfMonth();
        String dayBirthdayWithZeroes = String.format("%03d", dayBirthday);
        //month of Birthday
        String monthBirthday = birthdayLocalDate
                .getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        //year of Birthday
        String yearBirthday = String.valueOf(birthdayLocalDate.getYear());
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthBirthday);
        $(".react-datepicker__year-select").selectOption(yearBirthday);
        $(".react-datepicker__day--" + dayBirthdayWithZeroes + ":not(.react-datepicker__day--outside-month)").click();

        //Subjects
        int amountOfSubjects = 5;
        List<String> subjectsList = getRandomListStringsDesiredSize(TestData.INPUT_ARRAY_SUBJECTS, amountOfSubjects);
        for (int i = 0; i < amountOfSubjects; i++) {
            $("#subjectsInput").sendKeys(subjectsList.get(i));
            $("#subjectsInput").pressEnter();
        }

        //Hobbies
        int amountOfHobbies = 3;
        List<String> hobbiesList = getRandomListStringsDesiredSize(TestData.INPUT_ARRAY_OF_HOBBIES, amountOfHobbies);
        for (int i = 0; i < amountOfHobbies; i++) {
            $("#hobbiesWrapper").$(byText(hobbiesList.get(i))).click();
        }

        //Picture
        String picture = getRandomFromInputArray(TestData.INPUT_ARRAY_OF_PICTURES);
        String picturePath = "img/" + picture;
        $("#uploadPicture").uploadFromClasspath(picturePath);

        //Current Address
        String address = getRandomMessage(2, 4);
        $("#currentAddress").setValue(address);

        //State and City
        String state = getRandomFromInputArray(TestData.INPUT_STATES);
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        String city = getRandomFromInputArray(TestData.STATE_AND_CITY_MAP.get(state));
        $("#city").scrollTo().click();
        $("#stateCity-wrapper").$(byText(city)).click();
        String stateAndCity = state + " " + city;

        //Клик Submit
        $("#submit").click();

        //Проверка вывода формы подтверждения
        String birthday = dayBirthday + " " + monthBirthday + "," + yearBirthday;
        String subjects = Joiner.on(", ").join(subjectsList);
        String hobbies = Joiner.on(", ").join(hobbiesList);
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
