package appium.goodparking.pages;

import appium.goodparking.untils.AppDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BasePage
 * @Description 页面对象基础类
 * @Author wangx
 * @Date 2019/11/11 11:56
 * @Version 1.0
 **/

public class BasePage {
    public static AndroidDriver driver;

    public static void startApp() {
        driver = AppDriver.androidCreat();

        // 等待出现首页个人头像按钮、或者更新弹窗出现
        new WebDriverWait(driver,30).
                until(x -> {
                    String xml = driver.getPageSource();
                    if (xml.contains("ll_guide_points")){
                        // 如果存在启动页，则点击关闭按钮
                        findElementAndClick(By.id("com.innotek.goodparking:id/iv_close"));
                        return false;
                    }
                    return (xml.contains("iv_left") || xml.contains("tv_cancel"));
                });
    }

    public static WebElement findElement(By by) {
        try {
            return driver.findElement(by);
        } catch (Exception e) {
            handleAlertByPageSource();
            return driver.findElement(by);
        }
    }

    /**
     查找元素并点击
     */
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

    /**
     黑名单弹框处理
     */
    public static void handleAlertByPageSource(){
        String xml=driver.getPageSource();
        HashMap<String, By> alertBoxs=new HashMap<>();
        // 升级弹框关闭
        alertBoxs.put("com.innotek.goodparking:id/tv_cancel", By.id("com.innotek.goodparking:id/tv_cancel"));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        for (String key : alertBoxs.keySet()){
            if(xml.contains(key)){
                driver.findElement(alertBoxs.get(key)).click();
            }
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


    }

    /**
     判断元素是否存在
     */
    public static Boolean isElement(By by){
        boolean flag = false;
        try {
            findElement(by);
            flag = true;
        }catch (NoSuchElementException e){
            System.out.println(String.format("没有找到元素：%s",by));
        }
            return flag;
    }

    /**
     页面按比例滑动(传入参数为0-1之间的数)
     PS:经试验，Android5.0的机器无法使用该方案，可使用 scrollToElementForVertical 方法
     * @param startX 开始的X轴百分比(0-1)
     * @param startY 开始的Y轴百分比(0-1)
     * @param endX 结束的X轴百分比(0-1)
     * @param endY 结束的Y轴百分比(0-1)
     */

    public static void swipUp(Double startX, Double startY, Double endX, Double endY){
        Dimension size = driver.manage().window().getSize();
        System.out.println(size.width);
        System.out.println(size.height);
        TouchAction touchAction = new TouchAction(driver);
        touchAction.longPress(PointOption.point((int) (size.width*startX), (int) (size.height*startY))).
                waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).
                moveTo(PointOption.point((int) (size.width*endX), (int) (size.height*endY))).
                release().
                perform();
    }

    /**
     * 垂直滚动到具体元素上(多用于菜单的滚动)
     * @param scrollElementById 可滚动条的元素(ID定位)
     * @param toElementText 需要滚动到那个元素(text)
     */
    public static void scrollToElementForVertical(String scrollElementById, String toElementText){
        String formatScrollString = String.format("new UiScrollable(new UiSelector().resourceId(\"%s\")).scrollIntoView(" +
                "new UiSelector().text(\"%s\"));", scrollElementById, toElementText);
        driver.findElementByAndroidUIAutomator( // 滚动(android)
                formatScrollString);

    }



    public static void quit() {
        driver.quit();
    }
}
