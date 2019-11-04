package selenium.page;

import org.openqa.selenium.By;

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

}
