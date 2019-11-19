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
    private static App app;
    public static App getInstance() {
        if (app==null) {
            app=new App();
        }
        return app;
    }

    public SearchPage toSearch() {
        parseSteps();
        return new SearchPage();
    }

    public StockPage toStock() {
        parseSteps();
        return new StockPage();
    }

}
