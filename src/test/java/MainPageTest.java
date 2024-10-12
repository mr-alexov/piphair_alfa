import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        sleep(1000);
    }

    @Test
    void checkButtonsOnMainPage() {
        open("https://piphair.ru/");
        $("div[class*='positionstatic'] a[data-buttonfieldset='button']").shouldBe(visible);
        $("div[class*='positionstatic'] a[data-buttonfieldset='button']").shouldHave(text("Записаться"));
        $x("//a[contains(text(),'ЗАПИСАТЬСЯ НА УСЛУГИ')]").shouldBe(visible);
    }

    @Test
    void goToHaircutsCheckButtons() {
        open("https://piphair.ru/");
        $x("//div[contains(@class,'positionstatic')]//a[contains(text(),'Стрижки и укладки')]").click();

        // Переходим на другую страницу

        $x("//a[contains(text(),'ЗАПИСАТЬСЯ НА УСЛУГИ')]").shouldBe(interactable); // Кнопка сверху работает


        // Вставляем ожидание пока страница догружается до состояния готовности
        sleep(5000);

        // Выполняем прокрутку
        executeJavaScript("window.scrollBy(0, 1500);");

        $("[field='btitle'] span").shouldBe(interactable);

        // Ожидание просто чтобы посмотреть
        sleep(3000);

        $("[class*='pricelist-item__title']").shouldHave(text("стрижка"));

        $("[class*='pricelist-item__title']").parent().$("[class*='pricelist-item__price']").shouldHave(text("00₽"));

    }
}
