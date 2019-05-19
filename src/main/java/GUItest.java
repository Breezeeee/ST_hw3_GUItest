import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class GUItest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver chrome_driver = new ChromeDriver();
        chrome_driver.get("https://www.wjx.cn/jq/39655995.aspx");

        WebElement text = chrome_driver.findElement(By.name("q1"));
        text.sendKeys("test");

        Select select = new Select(chrome_driver.findElement(By.name("q2")));
        select.selectByIndex(1);

        WebElement radio = chrome_driver.findElement(By.xpath("//a[contains(@rel,'q3_3')]"));
        radio.click();

        chrome_driver.findElement(By.id("divSlider4_wrapper")).click();
        chrome_driver.switchTo().frame(60);

        //text.submit();
        //chrome_driver.quit();
    }
}
