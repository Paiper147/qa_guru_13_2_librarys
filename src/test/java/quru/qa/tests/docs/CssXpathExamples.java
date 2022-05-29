package quru.qa.tests.docs;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CssXpathExamples {

    void cssXpathExamples(){
        // <input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email">

        //data-testid - id, который был создан специально для автотестировщиков
        $("data-testid = email").setValue("123");
        $(by("data-testid", "email")).setValue("123");//--------------- для "data-testid" использовать ЭТО

        // <input type="email" class="inputtext login_form_input_box" name="email" id="email">
        //через cssSelector
        $("id = email").setValue("123");
        $("#email").setValue("123"); //--------------- для Просто id использовать ЭТО
        $(byId("email")).setValue("123");
        $(By.id("email")).setValue("123");
        $(by("id", "email")).setValue("123");

        //через xPath
        $x("//*[@id = 'email']").setValue("123");
        $(byXpath("//*[@id = 'email']")).setValue("123");

        // <input type="email" class="inputtext login_form_input_box" name="email">
        $("[name = email]").setValue("123");
        $(byName("email")).setValue("123");//--------------- для "name" использовать ЭТО

        // <input type="email" class="inputtext login_form_input_box">
        $("[class = inputtext login_form_input_box]").setValue("123");
        $(".login_form_input_box").setValue("123");//--------------- для "class" использовать ЭТО
        $(".inputtext.login_form_input_box").setValue("123");
        //- указываем ещё и тип <input
        $("input.inputtext.login_form_input_box").setValue("123");
        //через xPath
        $x("//input[@class = 'login_form_input_box']").setValue("123");
        //- вложенные классы
        $x("//input[@class = 'inputtext'][@class = 'login_form_input_box']").setValue("123");

        // <div class="inputtext">
        //      <input type="email" class="login_form_input_box" name="email">
        // </div>
        //обрати внимание на ПРОБЕЛ между ".inputtext" и ".login_form_input_box"
        $(".inputtext .login_form_input_box").setValue("123");//---- для вложенных классов использовать ЭТО

        //           ============  Самый Крупный локатор  ============
        // <input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email">
        $("input.inputtext.login_form_input_box#email[name=email][data-testid=email]").setValue("123");

        // <div>Hello qa.guru</div>
        //Поиск по тексту
        $x("//div[text()='Hello qa.guru']");
        $x("//div[contains(text(),'ello qa.gur')]");
        $x("//div[text()[contains(.,'ello qa.gur')]]");
        $(byText("Hello qa.guru")); //----- для поиска по ПОЛНОМУ соответствию текста
        $(withText("ello qa.gur")); //----- для поиска по ЧАСТИЧНОМУ соответствию текста


    }
}
