package appium.xiuqiu.pages;

import org.openqa.selenium.By;

/**
 * @ClassName HomePage
 * @Description 首页
 * @Author wangx
 * @Date 2019/11/7 12:08
 * @Version 1.0
 **/

public class App extends BasePage {

    public static SearchPage toSearch() {
        findElementAndClick(By.id("com.xueqiu.android:id/tv_search"));
        return new SearchPage();
    }

    public static StockPage toStock() {
        findElementAndClick(By.xpath("//*[contains(@resource-id,'tab_name') and @text='自选']"));
        return new StockPage();
    }

}
