package appium.xiuqiu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StockPage
 * @Description 自选页面操作
 * @Author wangx
 * @Date 2019/11/9 14:43
 * @Version 1.0
 **/

public class StockPage extends BasePage{
    public StockPage deleteAll() {  // 若有数据，则先取消所有关注
        parseSteps();
        return this;
    }

    public List<String> getAllStocks() {

        handleAlertByPageSource();
        List<String> stocks=new ArrayList<>();
        findElements(By.id("com.xueqiu.android:id/portfolio_stockName")).forEach(element -> {
            stocks.add(((WebElement)element).getText());
        });
        return stocks;
    }

    public StockPage addDefaultSelectedStocks(){ // 加入自选股
        parseSteps();
        return this;
    }

    public SearchPage toSearch(){
        parseSteps();
        return new SearchPage();
    }

}
