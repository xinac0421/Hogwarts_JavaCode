package appium.xiuqiu.pages;

import org.openqa.selenium.By;

import java.util.HashMap;

/**
 * @ClassName SearchPage
 * @Description TODO
 * @Author wangx
 * @Date 2019/11/9 10:38
 * @Version 1.0
 **/

public class SearchPage extends BasePage{

    public SearchPage search(String words) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("keyword", words);
        setParams(data);
        parseSteps();
        return this;
    }

    public Float getText() {  // 获取查询结果的股价
        parseSteps();
        return Float.valueOf(getResult().get("price").toString());
    }

    public SearchPage cancel(){  // 从结果页点击取消按钮，会返回上一个页面
        parseSteps();
        return this;
    }

    public SearchPage select(){  // 点击加入自选按钮
        parseSteps();
        return this;
    }
}
