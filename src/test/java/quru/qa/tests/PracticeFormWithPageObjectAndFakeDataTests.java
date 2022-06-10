package quru.qa.tests;

import com.github.javafaker.Faker;
import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import static utils.RandomUtils.*;

public class PracticeFormWithPageObjectAndFakeDataTests extends TestBase {

    @Test
    void successfulTestPracticeForm() {
        Faker faker = new Faker(new Locale("en"));

        String name = TestData.NAME;
        String lastName = faker.name().lastName();
        String email = getRandomEmail();
        String gender = getRandomFromInputArray(TestData.INPUT_ARRAY_GENDERS);
        String mobileNumber = getRandomNumberDesiredSize(10);
        practiceFormPage.openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobileNumber(mobileNumber);

        //Date of Birth
        LocalDate birthdayLocalDate = getRandomBirthday();
        int dayBirthday = birthdayLocalDate.getDayOfMonth();
        String dayBirthdayWithZeroes = String.format("%02d", dayBirthday);
        //month of Birthday
        String monthBirthday = birthdayLocalDate
                .getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        //year of Birthday
        String yearBirthday = String.valueOf(birthdayLocalDate.getYear());
        practiceFormPage.setDateOfBirth(dayBirthdayWithZeroes, monthBirthday, yearBirthday);

        //Subjects
        int amountOfSubjects = 5;
        List<String> subjectsList = getRandomListStringsDesiredSize(TestData.INPUT_ARRAY_SUBJECTS, amountOfSubjects);
        for (int i = 0; i < amountOfSubjects; i++) {
            practiceFormPage.setSubjects(subjectsList.get(i));
        }

        //Hobbies
        int amountOfHobbies = 3;
        List<String> hobbiesList = getRandomListStringsDesiredSize(TestData.INPUT_ARRAY_OF_HOBBIES, amountOfHobbies);
        for (int i = 0; i < amountOfHobbies; i++) {
            practiceFormPage.setHobbies(hobbiesList.get(i));
        }

        //Picture
        String picture = getRandomFromInputArray(TestData.INPUT_ARRAY_OF_PICTURES);
        String picturePath = "img/" + picture;
        practiceFormPage.setPicture(picturePath);

        //Current Address
        String address = getRandomMessage(2, 4);
        practiceFormPage.setCurrentAddress(address);

        //State and City
        String state = getRandomFromInputArray(TestData.INPUT_STATES);
        practiceFormPage.setState(state);
        String city = getRandomFromInputArray(TestData.STATE_AND_CITY_MAP.get(state));
        practiceFormPage.setCity(city);

        //Клик Submit
        practiceFormPage.submitClick();

        //Проверка вывода формы подтверждения
        checkResult(
                name,
                lastName,
                email,
                gender,
                mobileNumber,
                dayBirthday,
                monthBirthday,
                yearBirthday,
                subjectsList,
                hobbiesList,
                picture,
                address,
                state,
                city
        );

        //Клик Close
        practiceFormPage.closeClick();
    }

    private void checkResult(String name, String lastName, String email, String gender,
                             String mobileNumber, int dayBirthday, String monthBirthday,
                             String yearBirthday, List<String> subjectsList, List<String> hobbiesList,
                             String picture, String address, String state, String city) {
        String birthday = dayBirthday + " " + monthBirthday + "," + yearBirthday;
        String subjects = Joiner.on(", ").join(subjectsList);
        String hobbies = Joiner.on(", ").join(hobbiesList);
        String stateAndCity = state + " " + city;
        practiceFormPage.checkTitleOfResultPage("Thanks for submitting the form")
                .checkResult("Student Name", name + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobileNumber)
                .checkResult("Date of Birth", birthday)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", picture)
                .checkResult("Address", address)
                .checkResult("State and City", stateAndCity);
    }
}