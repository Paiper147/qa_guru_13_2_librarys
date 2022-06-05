package quru.qa.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import peges.PracticeFormPage;

public class TestBase {

    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1519x800";
    }

}
