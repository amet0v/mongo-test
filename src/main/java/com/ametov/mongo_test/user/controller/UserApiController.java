package com.ametov.mongo_test.user.controller;

import com.ametov.mongo_test.user.entity.UserDoc;
import com.ametov.mongo_test.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserApiController {
    private final UserRepository userRepository;

    @GetMapping("/")
    public UserDoc test(){
        UserDoc test1 = UserDoc.builder()
                .id(new ObjectId())
                .firstName("test")
                .lastName("test")
                .build();
        userRepository.save(test1);
        return test1;
    }
}
