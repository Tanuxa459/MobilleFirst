package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    @DisplayName("Первый тест")
    @Test
    void successfulSearchTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));

    }
    @DisplayName("Тест на проверку статьи")
    @Test
    void successfulSearchDaVinciTest() {
        step("Нажимаем на первое поисковое окошко и вводим в появившееся значение для  поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Leonardo da vinci");
        });
        step("Выбираем первую строчку в результатах поиска", () ->
                $(id("org.wikipedia.alpha:id/page_list_item_title")).click());

        step("Переходим в статью и проверяем заголовок об ошибке", () ->
                $(className("android.widget.TextView")).shouldHave(text("An error occurred")));

    }
}
