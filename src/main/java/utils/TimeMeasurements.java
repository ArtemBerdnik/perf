package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TimeMeasurements {

    @FindBy(css = "[class='home-link home-link_black_yes traffic__rate']")
    private static WebElement test;

    public static Object[] howLong(WebDriver driver, String url) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();

        ArrayList<String> arr = new ArrayList<>();
        arr.add(String.valueOf(localDate));

        long start = System.currentTimeMillis();
        driver.get(url);
        long finish = System.currentTimeMillis();

        arr.add(String.format("%d", TimeUnit.MILLISECONDS.toSeconds(finish - start)));

        start = System.currentTimeMillis();
        test.click();
        finish = System.currentTimeMillis();

        arr.add(String.format("%d", TimeUnit.MILLISECONDS.toSeconds(finish - start)));

        return arr.toArray();
    }
}
