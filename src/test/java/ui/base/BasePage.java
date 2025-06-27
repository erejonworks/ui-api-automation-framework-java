package ui.base;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void navigateTo(String url) {
        driver.get(url);
    }
}
