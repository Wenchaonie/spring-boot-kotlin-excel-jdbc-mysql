package com.example.Model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class User {

    @Id
    public String  id;

    public String default_address;
    public String default_billing_address;
    public String  name;
    public String  mobile;
    public String  password;
    public String  email;
    public String balance;
    public String  status;
    public String  created_at;
    public String  updated_at;
    public String order_count;

    public String  nick;
    public String  headimage;
    public String  desc;
    public String  sex;
    public List<Address> address;

    public User() {

    }

//    public WowMongoUser(
//            String firstName,
//            String lastName
//    ) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, " +
                        "Name='%s'"
                ,
                id,
                name
        );
    }

}
