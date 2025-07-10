package ui.driver;

import config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Arrays;
import java.util.List;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver createDriver() {
        if (driver == null) {
            // Leer parámetros con fallback a .properties
            String browser = System.getProperty("browser", ConfigManager.getProperty("browser"));
            String headless = System.getProperty("headless", "false");

            System.out.println("[DriverFactory] Navegador: " + browser);
            System.out.println("[DriverFactory] Headless: " + headless);

            List<String> validBrowsers = Arrays.asList("chrome", "firefox", "edge");
            if (!validBrowsers.contains(browser.toLowerCase())) {
                throw new IllegalArgumentException("⚠️ Invalid Browser: '" + browser + "'. Use: chrome, firefox or edge.");
            }


/*            switch (browser.toLowerCase()) {
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
            }*/
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    if (headless.equalsIgnoreCase("true")) {
                        chromeOptions.addArguments("--headless=new", "--window-size=1920,1080");
                    } else {
                        chromeOptions.addArguments("--start-maximized");
                    }
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless.equalsIgnoreCase("true")) {
                        firefoxOptions.addArguments("-headless");
                        firefoxOptions.addArguments("--width=1920", "--height=1080");
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (headless.equalsIgnoreCase("true")) {
                        edgeOptions.addArguments("--headless=new", "--window-size=1920,1080");
                    } else {
                        edgeOptions.addArguments("start-maximized");
                    }
                    driver = new EdgeDriver(edgeOptions);
                    break;

                default:
                    throw new IllegalArgumentException("Browser no soportado: " + browser);
            }
        }
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
