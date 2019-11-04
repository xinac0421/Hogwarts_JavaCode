package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @ClassName MaterialPage
 * @Description 素材库界面封装
 * @Author wangx
 * @Date 2019/11/4 10:13
 * @Version 1.0
 **/

public class MaterialPage extends BasePage {
    public MaterialPage uploadPic(String path){  // 上传图片
        findElement(By.linkText("图片")).click();
        findElement(By.linkText("添加图片")).click();
        findElement(By.id("js_upload_input"), 0).sendKeys(path);
        for (int i = 0; i < 3; i++) {
            if (isHasElement(By.cssSelector(".material_pic_list_item"))){
                findElement(By.linkText("完成")).click();
                break;  // 存在则说明已上传，点击完成按钮并跳出循环
            }else{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    public MaterialPage addPicAndText(String title, String bodyText, String picPath) {  // 添加图文
        findElement(By.linkText("图文")).click();
        waitVisibilityAndClickable(By.linkText("添加图文"));
        findElement(By.linkText("添加图文")).click();

        // 添加正文内容
        List<WebElement> frame_list = findElements(By.tagName("iframe"));
        String frame_id = frame_list.get(0).getAttribute("id");

        driver.switchTo().frame(frame_id);  // 切换到第一个frame
        findElement(By.cssSelector(".msg_mpNewsEditor_frameBody")).sendKeys(bodyText);
        driver.switchTo().defaultContent();  // 切换回来

        // 添加标题
        findElement(By.cssSelector(".ww_editorTitle")).sendKeys(title);

        //frame框下拉动作
        ((JavascriptExecutor)(driver)).executeScript("arguments[0].scrollTo(0, 500)",findElement(By.id("js_mpNews_editor_wrap"),0));

        // 添加封面图
        findElement(By.linkText("添加封面图")).click();
        findElement(By.xpath("//*[@class='cropper_mainImgContainer']//input"),0).sendKeys(picPath);
        findElement(By.linkText("确定")).click();

        //等待上传封面对话框消失
        for (int i = 0; i < 3; i++) {
            if (isHasElement(By.cssSelector(".cropper_mainImgContainer"))){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                break;
            }
        }


        //点击完成
        findElement(By.linkText("完成")).click();

        return this;
    }

}
