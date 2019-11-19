package appium.xiuqiu.pages;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName TestCaseStep
 * @Description TODO
 * @Author wangx
 * @Date 2019/11/18 14:57
 * @Version 1.0
 **/

public class PageObjectMethod {

    public List<HashMap<String,String>> steps;

    public List<HashMap<String, String>> getSteps() {
        return steps;
    }

    public void setSteps(List<HashMap<String, String>> steps) {
        this.steps = steps;
    }
}
