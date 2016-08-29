package com.example;

import com.example.settings.SettingsQiniu;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import com.qiniu.util.Auth;
import com.qiniu.http.Response;

@Component
@RestController
public class QiniuController {

    @Autowired
    public SettingsQiniu settingQiniu;

    @RequestMapping("/qiniutoken")
    public String qiniutoken(@RequestParam("file_path") String file_path){

        //上传到七牛后保存的文件名
        String res          = getUpToken(file_path);
        return res;
    }

    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY   = "l4bcP6bByVSJWgqOeKxHGtCyXl3L3bWlLh9wOLYu";
    String SECRET_KEY   = "kevimwWUrbsidQLFRD00zadC0RSUt7qZOFHUW7OY";
    //要上传的空间
    String bucketname   = "usericon";

    //密钥配置
    Auth auth           = Auth.create(ACCESS_KEY, SECRET_KEY);

    // 覆盖上传
    public String getUpToken(String key){
        //<bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
        //如果希望只能上传指定key的文件，并且不允许修改，那么可以将下面的 insertOnly 属性值设为 1。
        //第三个参数是token的过期时间
        String bucket = bucketname + ":" + key;
        System.out.println(bucket);
        return auth.uploadToken(bucketname, bucket, 3600, new StringMap().put("insertOnly", 1 ));
    }

}
