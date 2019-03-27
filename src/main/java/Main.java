import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ExcelReaderWriter;
import utils.TimeMeasurements;

import java.io.IOException;

public class Main {

    private WebDriver driver;

    private TimeMeasurements page;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        page = PageFactory.initElements(driver, TimeMeasurements.class);
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }

    @Test
    public void checkMe() throws IOException {
        ExcelReaderWriter.writeToXlsx( TimeMeasurements.howLong(driver, "https://yandex.ru"));

    }

    public static void main(String[] args) throws IOException {
       // ExcelReaderWriter.readFromXlsx();
    }
}
