package com.ametov.mongo_test.user.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserRequest {
    private String firstName;
    private String lastName;
}
