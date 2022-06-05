package quru.qa.tests;

import org.junit.jupiter.api.Test;

public class PracticeFormWithPageObjectsTests extends TestBase {

    @Test
    void successfulTestPracticeForm() {

        String name = "myName";
        String lastName = "myLastName";
        String email = "myEmail@mail.com";
        String gender = "Female";
        String mobileNumber = "0123456789";
        String birthday = "30 August,1992";
        String dayBirthday = "30";
        String monthBirthday = "August";
        String yearBrthday = "1992";
        String subjects = "Maths";
        String hobbies = "Reading";
        String picture = "1.png";
        String picturePath = "img/1.png";
        String address = "my currentAddress";
        String state = "Haryana";
        String city = "Panipat";
        String stateAndCity = "Haryana Panipat";

        practiceFormPage.openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobileNumber(mobileNumber)
                .setDateOfBirth(dayBirthday, monthBirthday, yearBrthday)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .setPicture(picturePath)
                .setCurrentAddress(address)
                .setState(state)
                .setCity(city)
                .submitClick();

        //Проверка вывода формы подтверждения
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

        //Клик Close
        practiceFormPage.closeClick();
    }
}
