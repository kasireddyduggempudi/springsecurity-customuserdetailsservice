package com.vedantu.daos;

import com.vedantu.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserDao {

    @Autowired
    private MongoConnection mongoConnection;

    public MongoOperations getMongoOperations(){
        return mongoConnection.getMongoOperations();
    }

    public UserModel getUserDetailsByUsername(String username){
        /*System.out.println("hello dao");*/
        Query query = new Query(Criteria.where("username").is(username));
        return this.getMongoOperations().findOne(query, UserModel.class, "users");
    }
}
