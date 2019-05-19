import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class GUItest {

    //等待函数
    private static void wait_for_next(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //配置chrome driver
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

        //启动chrome，最大化，并打开待测网站
        WebDriver chrome_driver = new ChromeDriver();
        chrome_driver.manage().window().maximize();
        chrome_driver.get("https://www.wjx.cn/jq/39655995.aspx");

        //下拉框测试：
        //选择选项1，再选择选项2，再选择选项3
        //结果应该为选项3
        Select select = new Select(chrome_driver.findElement(By.name("q2")));
        select.selectByIndex(1);
        wait_for_next(500);
        select.selectByIndex(2);
        wait_for_next(500);
        select.selectByIndex(3);
        wait_for_next(500);


        //单选框测试：
        //选择选项3，再选择选项1，再选择选项2
        //结果应该为选项2
        chrome_driver.findElement(By.xpath("//a[contains(@rel,'q3_3')]")).click();
        wait_for_next(500);
        chrome_driver.findElement(By.xpath("//a[contains(@rel,'q3_1')]")).click();
        wait_for_next(500);
        chrome_driver.findElement(By.xpath("//a[contains(@rel,'q3_2')]")).click();
        wait_for_next(500);

        //多选框测试
        //选择选项1、2、3，再点击选项3
        //结果应该为选项1和2
        List<WebElement> checkboxes = chrome_driver.findElements(By.className("jqCheckbox"));
        for (WebElement checkbox : checkboxes) {
            checkbox.click();
            wait_for_next(500);
        }
        checkboxes.get(2).click();
        wait_for_next(500);

        //将页面滚动到底部
        ((JavascriptExecutor)chrome_driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
        wait_for_next(1000);

        //滑动条测试
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
        wait_for_next(500);

        //文件上传测试：
        //先上传一个超过4M（文件大小限制）的文件，出现错误提示，点击确认
        //然后上传一个4M以内的文件，再上传另外一个4M以内的文件
        //最终结果应该为最后一个上传的文件
       chrome_driver.switchTo().frame(0);
        WebElement adFileUpload = chrome_driver.findElement(By.id("fileUpload"));
        adFileUpload.sendKeys("D:\\Proj\\SoftwareTest\\ST_hw3_GUItest\\src\\main\\resources\\2.jpg");
        wait_for_next(2000);
        Alert alert1 = chrome_driver.switchTo().alert();
        alert1.accept();
        wait_for_next(500);
        adFileUpload = chrome_driver.findElement(By.id("fileUpload"));
        adFileUpload.sendKeys("D:\\Proj\\SoftwareTest\\ST_hw3_GUItest\\src\\main\\resources\\test.txt");
        wait_for_next(2000);
        adFileUpload = chrome_driver.findElement(By.id("fileUpload"));
        adFileUpload.sendKeys("D:\\Proj\\SoftwareTest\\ST_hw3_GUItest\\src\\main\\resources\\1.jpg");
        chrome_driver.switchTo().defaultContent();
        wait_for_next(500);

        //问卷星给出的商品列表功能的测试：
        //第一个商品填写7，并且点击“-”按钮
        //第二个商品点击3次“+”按钮
        //第三个商品输入“x-2”，错误输入
        //结果应该为：6，3，0
        List<WebElement> goods = chrome_driver.findElements(By.className("item_select"));
        WebElement good1 = goods.get(0);
        WebElement good2 = goods.get(1);
        WebElement good3 = goods.get(2);
        good1.findElement(By.className("itemnum")).sendKeys("7");
        wait_for_next(500);
        good1.findElement(By.className("remove")).click();
        wait_for_next(500);
        for (int i = 0; i < 3; i++) {
            good2.findElement(By.className("add")).click();
            wait_for_next(500);
        }
        good3.findElement(By.className("itemnum")).sendKeys("x-2");
        wait_for_next(500);

        //此时必填信息不全，测试提交
        chrome_driver.findElement(By.id("submit_button")).click();
        wait_for_next(1000);

        //出现alert，点击确认
        Alert alert = chrome_driver.switchTo().alert();
        alert.accept();
        wait_for_next(500);

        //补全必填信息，即输入框测试
        WebElement text = chrome_driver.findElement(By.name("q1"));
        text.sendKeys("test");
        wait_for_next(500);

        //提交按钮测试
        chrome_driver.findElement(By.id("submit_button")).click();
        wait_for_next(5000);

        //关闭浏览器
        chrome_driver.quit();
    }
}
