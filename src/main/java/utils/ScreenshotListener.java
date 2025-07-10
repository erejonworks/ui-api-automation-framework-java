package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ui.driver.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver(); // necesitas agregar este método estático si no existe aún
        if (driver != null) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshot = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            String testName = result.getMethod().getMethodName();
            String fileName = "screenshots/" + testName + "_" + timestamp + ".png";

            try {
                Files.createDirectories(Paths.get("screenshots"));
                Files.copy(screenshot.toPath(), Paths.get(fileName));
                System.out.println("[ScreenshotListener] Screenshot guardada en: " + fileName);
            } catch (IOException e) {
                System.err.println("[ScreenshotListener] Error al guardar screenshot: " + e.getMessage());
            }
        }
    }
}
