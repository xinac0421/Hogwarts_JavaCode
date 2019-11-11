package appium.xiuqiu.pages;

import org.openqa.selenium.By;

/**
 * @ClassName SearchPage
 * @Description TODO
 * @Author wangx
 * @Date 2019/11/9 10:38
 * @Version 1.0
 **/

public class SearchPage extends BasePage{

    public SearchPage search(String words) {
        findElement(By.id("com.xueqiu.android:id/search_input_text")).sendKeys(words);
        findElement(By.xpath("//*[@resource-id='com.xueqiu.android:id/listview']/android.widget.RelativeLayout[1]")).click();
        return this;
    }

    public Float getText() {  // 获取查询结果的股价
        String price = findElement(By.id("com.xueqiu.android:id/current_price")).getText();
        return Float.valueOf(price);
    }

    public SearchPage cancel(){  // 从结果页点击取消按钮，会返回上一个页面
        findElementAndClick(By.id("com.xueqiu.android:id/action_close"));
        return this;
    }

    public SearchPage select(){  // 点击加入自选按钮
        findElementAndClick(By.id("com.xueqiu.android:id/follow_btn"));
        return this;
    }
}
