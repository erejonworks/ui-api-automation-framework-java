package ui.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver createDriver(String browser) {
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--start-maximized");
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "chrome-headless":
                    ChromeOptions headlessOptions = new ChromeOptions();
                    headlessOptions.addArguments("--headless=new");
                    headlessOptions.addArguments("--window-size=1920,1080");
                    driver = new ChromeDriver(headlessOptions);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--width=1920", "--height=1080");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("start-maximized");
                    driver = new EdgeDriver(edgeOptions);
                    break;

                // Puedes agregar Firefox, Edge, etc. aquí
                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
