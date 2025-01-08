package com.ametov.mongo_test.user.controller;

import com.ametov.mongo_test.user.dto.request.CreateUserRequest;
import com.ametov.mongo_test.user.dto.response.UserResponse;
import com.ametov.mongo_test.user.entity.UserDoc;
import com.ametov.mongo_test.user.exception.ObjectIdParseException;
import com.ametov.mongo_test.user.exception.UserNotFoundException;
import com.ametov.mongo_test.user.repository.UserRepository;
import com.ametov.mongo_test.user.routes.UserRoutes;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UserApiController {
    private final UserRepository userRepository;

    @GetMapping("/")
    public UserDoc test() {
        UserDoc test1 = UserDoc.builder()
                .id(new ObjectId())
                .firstName("test")
                .lastName("test")
                .build();
        userRepository.save(test1);
        return test1;
    }

    @PostMapping(UserRoutes.CREATE)
    public UserResponse create(@RequestBody CreateUserRequest request) {
        UserDoc userDoc = UserDoc.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        userDoc = userRepository.save(userDoc);
        return UserResponse.of(userDoc);
    }

    @GetMapping(UserRoutes.BY_ID)
    public UserResponse findById(@PathVariable String id) throws UserNotFoundException, ObjectIdParseException {
        if (!ObjectId.isValid(id)) throw new ObjectIdParseException();
        UserDoc userDoc = userRepository.findById(new ObjectId(id)).orElseThrow(UserNotFoundException::new);
        return UserResponse.of(userDoc);
    }

    @GetMapping(UserRoutes.SEARCH)
    public List<UserResponse> search(@RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserDoc> users = userRepository.findAll(pageable);

        return users.getContent().stream().map(UserResponse::of).collect(Collectors.toList());
    }
}
