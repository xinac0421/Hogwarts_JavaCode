package appium.goodparking.untils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Driver
 * @Description 驱动配置
 * @Author wangx
 * @Date 2019/11/7 11:30
 * @Version 1.0
 **/

public class AppDriver {

    public static AndroidDriver androidCreat() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appPackage", "com.innotek.goodparking");
        desiredCapabilities.setCapability("appActivity", ".activity.SplashActivity");
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "HUAWEI_MT7_TL00");
        desiredCapabilities.setCapability("udid", "P4M0215528000176");
        desiredCapabilities.setCapability("automationName", "uiautomator2");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("unicodeKeyboard", true);
        desiredCapabilities.setCapability("resetKeyboard",true);

        // 设置自动到目录下查找相对应的chromedriver版本
        desiredCapabilities.setCapability("chromedriverExecutableDir", "/Users/wangxin/Documents/TOOLS/ChromeDriver/");
        URL url = null;
        try {
            url = new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        assert url != null;
        AndroidDriver driver = new AndroidDriver(url, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

}
