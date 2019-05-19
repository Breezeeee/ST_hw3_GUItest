import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class GUItest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver chrome_driver = new ChromeDriver();
        chrome_driver.get("https://www.wjx.cn/jq/39655995.aspx");

        Select select = new Select(chrome_driver.findElement(By.name("q2")));
        select.selectByIndex(1);

        WebElement radio = chrome_driver.findElement(By.xpath("//a[contains(@rel,'q3_3')]"));
        radio.click();

        List<WebElement> checkboxes = chrome_driver.findElements(By.className("jqCheckbox"));
        for (WebElement checkbox : checkboxes) {
            checkbox.click();
        }

        {
            WebElement element = chrome_driver.findElement(By.id("divSlider5_bar"));
            Actions builder = new Actions(chrome_driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = chrome_driver.findElement(By.cssSelector(".cm:nth-child(9)"));
            Actions builder = new Actions(chrome_driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = chrome_driver.findElement(By.cssSelector(".cm:nth-child(9)"));
            Actions builder = new Actions(chrome_driver);
            builder.moveToElement(element).release().perform();
        }

        chrome_driver.switchTo().frame(0);
        WebElement adFileUpload = chrome_driver.findElement(By.id("fileUpload"));
        adFileUpload.sendKeys("D:\\Proj\\SoftwareTest\\ST_hw3_GUItest\\src\\main\\resources\\1.jpg");
        chrome_driver.switchTo().defaultContent();

        List<WebElement> goods = chrome_driver.findElements(By.className("item_select"));
        //System.out.println(goods.size());
        WebElement good1 = goods.get(0);
        WebElement good2 = goods.get(1);
        WebElement good3 = goods.get(2);
        good1.findElement(By.className("itemnum")).sendKeys("7");
        good1.findElement(By.className("remove")).click();
        for (int i = 0; i < 3; i++) {
            good2.findElement(By.className("add")).click();
        }
        good3.findElement(By.className("itemnum")).sendKeys("-2");

        chrome_driver.findElement(By.id("submit_button")).click();

//        chrome_driver.quit();

        Alert alert = chrome_driver.switchTo().alert();
        alert.accept();

        WebElement text = chrome_driver.findElement(By.name("q1"));
        text.sendKeys("test");

        chrome_driver.findElement(By.id("submit_button")).click();

        chrome_driver.quit();
    }
}
