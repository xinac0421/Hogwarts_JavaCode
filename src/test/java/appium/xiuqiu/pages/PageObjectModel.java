package appium.xiuqiu.pages;

import java.util.HashMap;

/**
 * @ClassName PageObjectModel
 * @Description TODO
 * @Author wangx
 * @Date 2019/11/19 13:50
 * @Version 1.0
 **/

public class PageObjectModel {
    public HashMap<String, PageObjectElement> elements = new HashMap<>();
    public HashMap<String, PageObjectMethod> methods = new HashMap<>();
    public PageObjectMethod getMethod(String method){
        return methods.get(method);
    }

}
