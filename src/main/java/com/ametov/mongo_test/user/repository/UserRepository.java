package com.ametov.mongo_test.user.repository;

import com.ametov.mongo_test.user.entity.UserDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<UserDoc, ObjectId> {
    public List<UserDoc> findByFirstName(String firstName);
    public List<UserDoc> findByLastName(String lastName);
}
