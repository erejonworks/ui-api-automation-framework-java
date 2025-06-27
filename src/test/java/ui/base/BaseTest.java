package ui.base;

import config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ui.driver.DriverFactory;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
//        driver = DriverFactory.createDriver("chrome");
//        driver.get(ConfigManager.get("base.url")); // 🔁 URL dinámico
        String browser = ConfigManager.get("browser");
        driver = DriverFactory.createDriver(browser);


    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
