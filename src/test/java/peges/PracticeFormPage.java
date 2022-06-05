package peges;

import com.codeborne.selenide.SelenideElement;
import peges.components.CalendarComponent;
import peges.components.ResultsTableComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class PracticeFormPage {

    CalendarComponent calendarComponent = new CalendarComponent();
    ResultsTableComponent resultsTableComponent = new ResultsTableComponent();
    SelenideElement firstNameInput = $("#firstName");
    SelenideElement lastNameInput =  $("#lastName");
    SelenideElement userEmailInput = $("#userEmail");
    SelenideElement genderInput = $("#genterWrapper");
    SelenideElement userNumberInput = $("#userNumber");
    SelenideElement subjectsInput = $("#subjectsInput");
    SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    SelenideElement hobbiesInput = $("#hobbiesWrapper");
    SelenideElement pictureInput = $("#uploadPicture");
    SelenideElement currentAddressInput = $("#currentAddress");

    public PracticeFormPage openPage(){
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#close-fixedban').remove()");

        return this;
    }

    public PracticeFormPage setFirstName(String value){
        firstNameInput.setValue(value);

        return this;
    }
    public PracticeFormPage setLastName(String value){
        lastNameInput.setValue(value);

        return this;
    }
    public PracticeFormPage setEmail(String value){
        userEmailInput.setValue(value);

        return this;
    }
    public PracticeFormPage setGender(String value){
        genderInput.$(byText(value)).click();

        return this;
    }
    public PracticeFormPage setMobileNumber(String value){
        userNumberInput.setValue(value);

        return this;
    }
    public PracticeFormPage setSubjects(String value){
        subjectsInput.sendKeys(value);
        subjectsInput.pressEnter();

        return this;
    }

    public PracticeFormPage setDateOfBirth(String day, String month, String year){
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }
    public PracticeFormPage setHobbies(String value){
        hobbiesInput.$(byText(value)).click();

        return this;
    }
    public PracticeFormPage setPicture(String value){
        pictureInput.uploadFromClasspath(value);

        return this;
    }
    public PracticeFormPage setCurrentAddress(String value){
        currentAddressInput.setValue(value);

        return this;
    }
    public PracticeFormPage setState(String value){
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(value)).click();

        return this;
    }
    public PracticeFormPage setCity(String value){
        $("#city").scrollTo().click();
        $("#stateCity-wrapper").$(byText(value)).click();

        return this;
    }
    public PracticeFormPage submitClick(){
        $("#submit").click();

        return this;
    }

    public PracticeFormPage checkTitleOfResultPage(String value){
        $("#example-modal-sizes-title-lg").shouldHave(text(value));

        return this;
    }
    public PracticeFormPage checkResult(String key, String value){
        resultsTableComponent.checkResult(key, value);

        return this;
    }

    public PracticeFormPage closeClick(){
        $("#closeLargeModal").click();

        return this;
    }
}
