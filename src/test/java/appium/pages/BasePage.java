package appium.pages;

import appium.utils.AppDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BasePage
 * @Description TODO
 * @Author wangx
 * @Date 2019/11/7 11:56
 * @Version 1.0
 **/

public class BasePage {
    private static AndroidDriver driver;

    public static void startApp() {
        driver = AppDriver.androidCreat();
    }

    public static WebElement findElement(By by) {

        return driver.findElement(by);
    }

    public static void findElementAndClick(By by) {

        try {
            driver.findElement(by).click();
        } catch (Exception e) {
            handleAlertByPageSource();

            driver.findElement(by).click();
        }
    }


    public static List findElements(By by) {
        return driver.findElements(by);
    }

    private static void handleAlertByPageSource(){
        //todo: xpath匹配， 标记 定位
        String xml=driver.getPageSource();
        List<String> alertBoxs=new ArrayList<>();
        alertBoxs.add("com.xueqiu.android:id/image_cancel");  // 升级框
        alertBoxs.add("com.xueqiu.android:id/ib_close");  // 广告
        //alertBoxs.add("com.xueqiu.android:id/snb_tip_text");

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        alertBoxs.forEach(alert -> {
            if(xml.contains(alert)){
                driver.findElement(By.id(alert)).click();
            }
        });
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }


    public static void quit() {
        driver.quit();
    }
}
