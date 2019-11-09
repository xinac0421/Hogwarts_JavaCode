package appium.pages;

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

    public static StockPage stockPage() {
        //findElementAndClick();
        return new StockPage();
    }

}
