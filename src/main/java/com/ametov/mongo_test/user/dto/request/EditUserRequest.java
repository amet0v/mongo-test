package com.ametov.mongo_test.user.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class EditUserRequest extends CreateUserRequest{
    private String id;
    
}
