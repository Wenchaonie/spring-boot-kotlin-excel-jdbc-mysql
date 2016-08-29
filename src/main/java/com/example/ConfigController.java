package com.example;

import com.example.settings.SettingRedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class ConfigController {

    @Autowired
    public SettingRedis settingRedis;

    @RequestMapping("/setting_redis")
    public String setting_redis(){

        return settingRedis.password;
    }


}
