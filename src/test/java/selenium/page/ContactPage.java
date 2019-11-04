package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.HashMap;
import java.util.List;

/**
 * @ClassName ContactPage
 * @Description 通信录页面成员操作封装
 * @Author wangx
 * @Date 2019/10/30 21:30
 * @Version 1.0
 **/

public class ContactPage extends BasePage{
    public ContactPage add(String name, String id, String phone){
        findElement(By.name("username")).sendKeys(name);
        findElement(By.name("acctid")).sendKeys(id);
        findElement(By.name("mobile")).sendKeys(phone);
        findElement(By.linkText("保存")).click();
        return this;
    }

    public ContactPage del(String keyword){
        findElement(By.id("memberSearchInput")).clear();
        findElement(By.id("memberSearchInput")).sendKeys(keyword);
        waitClickable(By.linkText("编辑"), 3);
        findElement(By.linkText("删除")).click();
        findElement(By.linkText("确认")).click();
        return this;
    }

    public ContactPage delOnePage(){
        waitVisibilityAndClickable(By.cssSelector(".ww_checkbox"));
        List<WebElement> elements=driver.findElements(By.cssSelector(".ww_checkbox"));
        for(int i=1;i<elements.size();i++){
            elements.get(i).click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        findElement(By.linkText("删除")).click();
        findElement(By.linkText("确认")).click();
        return this;
    }

    public ContactPage importFromFile(String path){
        findElement(By.partialLinkText("批量导入/导出")).click();
        findElement(By.linkText("文件导入")).click();
        findElement(By.id("js_upload_file_input"), 0).sendKeys(path);
        findElement(By.id("submit_csv")).click();
        if (isHasElement(By.linkText("成功"))){
            findElement(By.linkText("成功")).click();
        }else if(isHasElement(By.linkText("前往查看"))){
            findElement(By.linkText("前往查看")).click();
        }else {
            System.out.println("导入文件出错");
        }
        return this;
    }


    public void list(){

    }

    public HashMap<String, String> getUserInfo(String keyword){
        //todo:
        return new HashMap<>();
    }

}
