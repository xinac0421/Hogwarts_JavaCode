package appium.xiuqiu.pages;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName PageObjectElement
 * @Description TODO
 * @Author wangx
 * @Date 2019/11/19 14:00
 * @Version 1.0
 **/

public class PageObjectElement {
    public List<HashMap<String,String>> element;

    public By getLocator(){
        //todo: 遍历element里的所有元素，直到能匹配到元素的
        for(HashMap<String, String> map : element){
            By locator = getMapLocator(map);
            if (locator!=null){
                return locator;
            }
        }
        return null;
    }

    public By getLocator(String os, String version){
        for(HashMap<String, String> map : element){
            if(map.get("os").equals(os) && map.get("version").equals(version)){
                By locator = getMapLocator(map);
                if (locator!=null){
                    return locator;
                }
            }
        }
        return null;
    }

    private By getMapLocator(HashMap<String, String> map){
        if(map.containsKey("id") && BasePage.isElement(By.id(map.get("id")))){
            return By.id(map.get("id"));
        }else if(map.containsKey("xpath") && BasePage.isElement(By.xpath(map.get("xpath")))){
            return By.id(map.get("xpath"));
        }else if(map.containsKey("aid") && BasePage.isElement(MobileBy.AccessibilityId(map.get("aid")))){
            return MobileBy.AccessibilityId(map.get("aid"));
        }
        return null;
    }
}
