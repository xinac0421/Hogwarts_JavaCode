package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName BasePage
 * @Description TODO
 * @Author wangx
 * @Date 2019/10/30 21:59
 * @Version 1.0
 **/

public class BasePage {

    static WebDriver driver;

    public WebElement findElement(By by){
        waitClickable(by, 5);
        return driver.findElement(by);
    }

    public WebElement findElement(By by, int time){
        if (time > 0){
            waitClickable(by, time);
        }
        return driver.findElement(by);
    }

    public void startChrome(){
        System.setProperty("webdriver.chrome.driver", "tools/ChromeDriver/78.0.3904.70/chromedriver");
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.setCapability("pageLoadStrategy","none");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void waitClickable(By by, int time){
        new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitVisibilityAndClickable(By by){
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(by));
        new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(by));
    }

    public void quit(){
        driver.quit();
    }

    public boolean isHasElement(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            System.out.println("不存在此元素:"+ by.toString());
            return false;
        }
    }

    public void retryElementClick(By by) throws InterruptedException {
        // 重试点击操作,规避某些地方操作完成后有tips挡住元素无法点击的问题（该元素首先要先存在）
        boolean flag = false;
        for (int i = 1; i <= 3; i++) {
            try {
                driver.findElement(by).click();
                flag = true;
            }catch (ElementClickInterceptedException e){
                Thread.sleep(1000);
                System.out.println(String.format("元素：%s 点击失败，等待1秒重试，重试次数：%s", by, i));
            }
            if (flag){
                if (i >1){
                    System.out.println(String.format("重试点击操作成功，共重试次数：%s 次", (i-1)));
                }
                break;
            }
        }

    }



}
