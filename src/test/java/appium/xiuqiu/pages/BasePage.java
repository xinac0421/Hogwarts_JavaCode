package appium.xiuqiu.pages;

import appium.xiuqiu.utils.AppDriver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BasePage
 * @Description TODO
 * @Author wangx
 * @Date 2019/11/7 11:56
 * @Version 1.0
 **/

public class BasePage {
    public static AndroidDriver driver;
    public static HashMap<String, Object> params = new HashMap<>();
    public static HashMap<String, Object> result = new HashMap<>();
    private PageObjectModel model = new PageObjectModel();

    // 测试步骤参数化
    public static HashMap<String, Object> getParams() {
        return params;
    }
    public static void setParams(HashMap<String, Object> params) {
        BasePage.params = params;
    }

    // 测试步骤获取结果
    public static HashMap<String, Object> getResult() {
        return result;
    }

    public void startApp() {
        driver = AppDriver.androidCreat();
        // 等待首页加载完（标志为出现搜索框、升级框、广告框其一即可）
        new WebDriverWait(driver,30).
                until(x -> {
                    String xml = driver.getPageSource();
                    return (xml.contains("tv_search") || xml.contains("image_cancel") || xml.contains("ib_close"));
                });
    }

    public static WebElement findElement(By by) {

        return driver.findElement(by);
    }

    public static void findElementAndClick(By by) {

        try {
            driver.findElement(by).click();
        } catch (Exception e) {
            handleAlertByPageSource();

            driver.findElement(by).click();
        }
    }


    public static List findElements(By by) {
        return driver.findElements(by);
    }

    public static void handleAlertByPageSource(){
        String xml=driver.getPageSource();
        HashMap<String, By> alertBoxs=new HashMap<>();
        // 升级弹框关闭
        alertBoxs.put("com.xueqiu.android:id/image_cancel", By.id("com.xueqiu.android:id/image_cancel"));
        // 广告关闭
        alertBoxs.put("com.xueqiu.android:id/ib_close",By.id("com.xueqiu.android:id/ib_close"));
        // tips
        alertBoxs.put("com.xueqiu.android:id/snb_tip_text",By.id("com.xueqiu.android:id/snb_tip_text"));
        // 下次再说弹框
        alertBoxs.put("com.xueqiu.android:id/md_buttonDefaultNegative",By.id("com.xueqiu.android:id/md_buttonDefaultNegative"));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        for (String key : alertBoxs.keySet()){
            if(xml.contains(key)){
                if(key.equals("com.xueqiu.android:id/snb_tip_text")){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (findElement(alertBoxs.get(key)).isDisplayed()){
                        Dimension size=driver.manage().window().getSize();
                        new TouchAction<>(driver).tap(PointOption.point(size.width/2,size.width/2)).perform();
                    }
                }else {
                    driver.findElement(alertBoxs.get(key)).click();
                }

            }


        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


    }


    public void quit() {
        driver.quit();
    }


    public void parseSteps(){
        parseSteps(Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    /**
     * 步骤解析
     */
    public void parseSteps(String method){
        String path = "/" + this.getClass().getCanonicalName().replace(".","/")+ ".yaml";
        //TypeReference<HashMap<String, PageObjectMethod>> typeRef = new TypeReference<HashMap<String, PageObjectMethod>>(){};
        parseSteps(path, method);

    }


    public void parseSteps(String path, String method){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        //TypeReference<HashMap<String, PageObjectMethod>> typeRef = new TypeReference<HashMap<String, PageObjectMethod>>(){};
        try {
            model = mapper.readValue(BasePage.class.getResourceAsStream(path), PageObjectModel.class);
            parseStep(model.methods.get(method));  // 解析步骤

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private static void parseElements(){

    }

    /**
     * 具体步骤执行
     * @param steps 步骤
     */
    private void parseStep(PageObjectMethod steps){
        steps.getSteps().forEach(step->{
            WebElement element=null;
            if (step.containsKey("id")){
                element = driver.findElement(By.id(step.get("id")));
            }else if (step.containsKey("xpath")){
                element = driver.findElement(By.xpath(step.get("xpath")));

            }else if (step.containsKey("aid")){
                element = driver.findElement(MobileBy.AccessibilityId(step.get("aid")));
            }else if(step.containsKey("element")){
                element = driver.findElement(
                        model.elements.get(step.get("element")).getLocator());

            }else{
                assert false;
            }

            if (step.containsKey("send")){
                String words = step.get("send");
                for(Map.Entry<String, Object> kv: params.entrySet()){
                    String myParams = "${"+kv.getKey()+"}";
                    if (words.contains(myParams)){
                        System.out.println(kv);
                        words = words.replace(myParams,kv.getValue().toString());
                    }

                }
                element.sendKeys(words);
            }else if (step.containsKey("get")){
                String attribute = element.getAttribute(step.get("get"));
                System.out.println(attribute);
                result.put(step.get("dump"), attribute);
            }else {
                element.click();
            }
        });

    }

    /**
     判断元素是否存在
     */
    public static Boolean isElement(By by){
        boolean flag = false;
        try {
            findElement(by);
            flag = true;
        }catch (NoSuchElementException e){
            System.out.println(String.format("没有找到元素：%s",by));
        }
        return flag;
    }




}
