package quru.qa.tests;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {

    @BeforeAll
    static void beforeAll(){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1519x800";
    }

    @Test
    void successfullTest() {
        String name = "myName";

        //открытие браузера и URL
        open("/text-box");

        //закрытие рекламы - выполнение кода на стороне браузера
        executeJavaScript("$('footer').remove()");
//        executeJavaScript("$('#fixeban').remove()");

        //заполнение формы данными
        $("[id = userName]").setValue(name);
        $("[id = userEmail]").setValue("myEmail@name.ru");
        $("[id = currentAddress]").setValue("my currentAddress 1");
        $("[id = permanentAddress]").setValue("my permanentAddress 2");
        $("[id = submit]").click();

        //проверка формы ввода
        $("[id = output]").shouldHave(text(name),text("myEmail@name.ru"),
                text("my currentAddress 1"),text("my permanentAddress 2"));
    }
}
