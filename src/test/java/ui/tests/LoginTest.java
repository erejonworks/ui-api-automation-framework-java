package ui.tests;

import config.ConfigManager;
import config.Environment;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import ui.base.BaseTest;
import ui.pages.LoginPage;

import static org.testng.Assert.assertEquals;

@Epic("UI Testing")
@Feature("Login Functionality")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class LoginTest extends BaseTest {

    @Test(description = "Valid login with correct credentials")
    @Story("Valid login")
    @Severity(SeverityLevel.CRITICAL)
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        String baseURL = ConfigManager.get("base.url");
        String user = ConfigManager.get("username");
        String pwd = ConfigManager.get("password");
        loginPage.navigateTo(baseURL);
//        loginPage.navigateTo(Environment.BASE_UI_URL);
//        loginPage.login("standard_user", "secret_sauce");
        loginPage.login(user, pwd);

        String expectedUrl = Environment.BASE_UI_URL + "/inventory.html";
        assertEquals(driver.getCurrentUrl(), expectedUrl);
    }

}
