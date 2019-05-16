import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class GUItest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver chrome_driver = new ChromeDriver();
        chrome_driver.get("https://www.baidu.com");
//        WebElement text = chrome_driver.findElement(By.name("wd"));
//        text.sendKeys("百度");
//        text.submit();
//        chrome_driver.quit();
    }
}
