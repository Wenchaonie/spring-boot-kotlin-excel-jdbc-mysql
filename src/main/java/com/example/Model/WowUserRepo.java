package com.example.Model;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface WowUserRepo extends MongoRepository<User, String> {

    public User findByName(String name);
    public List<User> findListByName(String name);

}
