package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest extends TestBase {

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

    @Test
    void checkingOrdering() {
        open("https://piphair.ru/");

        // Ждем кнопку записаться на услуги.
        $x("//a[contains(text(),'ЗАПИСАТЬСЯ НА УСЛУГИ')]").shouldBe(interactable);

        // Нажимаем её.
        $x("//a[contains(text(),'ЗАПИСАТЬСЯ НА УСЛУГИ')]").click();

        // Ждём появления стрелочек которые разворачивают выбор
        $("ui-kit-svg-icon[name='arrow-down-light']").shouldBe(interactable);

        // Нажимаем первую стрелочку
        $("ui-kit-svg-icon[name='arrow-down-light']").click();

        // Кликаем первый чекбокс.
        $("input[type='checkbox']").click();

        // Кнопка продолжить должна быть доступна для действий
        $("button.full-width").shouldBe(interactable);

        // Жмем на кнопку Продолжить
        $("button.full-width").click();

        // Должны быть доступны дни для записи.
        $("div.current-week[data-locator='working_day']  div span[data-locator='working_day_number']").shouldBe(interactable);

    }
}
