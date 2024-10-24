package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.sleep;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.remote = System.getProperty("remoteServer");

        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 7000;
        sleep(1000);

    }


    @AfterEach
    void addAttachments() {

        try {
            Attach.screenshotAs("Last screen");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Attach.pageSource();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Attach.browserConsoleLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Attach.addVideo();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
