package com.example;

import com.example.Model.User;
import com.example.Model.WowUserRepo;
import com.example.settings.SettingsQiniu;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import com.qiniu.util.Auth;
import com.qiniu.http.Response;

@SpringBootApplication
@Component
@RestController
public class MongoMigrationUserController {

    @Autowired
    private WowUserRepo repository;

    @RequestMapping("/migration_mongo")
    public String migration_mongo(){

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");

        for (User user : this.repository.findAll()) {
            System.out.println(user);
        }
        System.out.println();

        return "123123213";

    }



}
