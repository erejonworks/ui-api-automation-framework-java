package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.base.BasePage;

import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    WebElement usernameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "login-button")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        if (driver== null)
            System.out.println("Driver is null");
        PageFactory.initElements(driver,this);
    }

    @Step("Login with user: {user}")
    public void login(String user, String pass) {
        System.out.println("LLEGO AL LOGIN");
        System.out.println("usernameInput"+ usernameInput);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(usernameInput));
        System.out.println("PASO EL WAIT");
        usernameInput.sendKeys(user);
        passwordInput.sendKeys(pass);
        loginButton.click();
    }
}
