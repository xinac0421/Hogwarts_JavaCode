package appium.goodparking.pages;

import org.openqa.selenium.By;

/**
 * @ClassName SearchPage
 * @Description 搜索页
 * @Author wangx
 * @Date 2019/11/12 10:30
 * @Version 1.0
 **/

public class SearchPage extends BasePage{

    public SearchPage search(String words) {  // 查找第一个结果并点击
        findElement(By.id("com.innotek.goodparking:id/edtPointOfInterest")).sendKeys(words);
        findElement(By.id("com.innotek.goodparking:id/tv_search")).click();  // 点击搜索按钮
        return this;
    }

    public String getListOneText() {  // 返回搜索结果的第一个数据
        return findElement(By.id("com.innotek.goodparking:id/tv_poi")).getText();
    }
}
