package com.example


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pingplusplus.Pingpp;
import org.springframework.beans.factory.annotation.Autowired
import com.example.settings.SettingsQiniu
import org.springframework.stereotype.Component

import org.litote.kmongo.*


@Component
@RestController

class HelloController {

    @RequestMapping("/hello")
    fun hello():String {
        return " withyou 1314243324";
    }

    @RequestMapping("/pingpp")
    fun pingpp(): String {

        val pingpp_app_id           = "app_4mTKGGTSiLOGvTWj";
        val pingpp_app_test_app_key = "sk_test_i1S0uPKW10mHHi1ir9CivLKC";
        val pingpp_app_live_app_key = "sk_live_POSa18Wnz9eHuDqXj9vbnbLO";
        val apiKey                  = pingpp_app_test_app_key;

        // 设置 API Key
        Pingpp.apiKey               = apiKey

        val e                       = ChargeExample(pingpp_app_id);
        val change_obj              = e.createCharge();
        return change_obj.toString();
    }


//    @RequestMapping("/migration_user")
//    fun migration_user(): String {
//
//        data class Jedi(val name: String, val age: Int)
//
//        val client      = KMongo.createClient("wowdsgn.com",27017)
//        val database    = client.getDatabase("wow")
//        val collection  = database.getCollection<WowUser>() //KMongo extension method
//
//        val res         = collection.find()
//
//        res.forEach {
//            print("Hello, $it!")
//        }
//        val ress        = res.json
//        return "123123213";
//    }


}









