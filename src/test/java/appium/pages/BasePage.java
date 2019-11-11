package appium.pages;

import appium.utils.AppDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
    public static AndroidDriver driver;

    public static void startApp() {
        driver = AppDriver.androidCreat();
        // 等待首页加载完（标志为出现搜索框、升级框、广告框其一即可）
        new WebDriverWait(driver,30).
                until(x -> {
                    String xml = driver.getPageSource();
                    return (xml.contains("tv_search") || xml.contains("image_cancel") || xml.contains("ib_close"));
                });
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

    public static void handleAlertByPageSource(){
        String xml=driver.getPageSource();
        HashMap<String, By> alertBoxs=new HashMap<>();
        // 升级弹框关闭
        alertBoxs.put("com.xueqiu.android:id/image_cancel", By.id("com.xueqiu.android:id/image_cancel"));
        // 广告关闭
        alertBoxs.put("com.xueqiu.android:id/ib_close",By.id("com.xueqiu.android:id/ib_close"));
        // tips
        alertBoxs.put("com.xueqiu.android:id/snb_tip_text",By.id("com.xueqiu.android:id/snb_tip_text"));
        // 下次再说弹框
        alertBoxs.put("com.xueqiu.android:id/md_buttonDefaultNegative",By.id("com.xueqiu.android:id/md_buttonDefaultNegative"));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        for (String key : alertBoxs.keySet()){
            if(xml.contains(key)){
                driver.findElement(alertBoxs.get(key)).click();
            }
            if(key.equals("com.xueqiu.android:id/snb_tip_text")){
                Dimension size=driver.manage().window().getSize();
                new TouchAction<>(driver).tap(PointOption.point(size.width/2,size.width/2)).perform();
            }

        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


    }


    public static void quit() {
        driver.quit();
    }
}
