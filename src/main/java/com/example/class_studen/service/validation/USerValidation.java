package com.example.class_studen.service.validation;

import com.example.class_studen.dto.ErrorsDto;
import com.example.class_studen.dto.UserDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class USerValidation {

    public List<ErrorsDto> validate(UserDto dto) {
        List<ErrorsDto> errors = new ArrayList<>();

        if (StringUtils.isBlank(dto.getUsername())) {
            errors.add(new ErrorsDto("username cannot be null or empty", "username"));
        }
        if (StringUtils.isBlank(dto.getPassword())) {
            errors.add(new ErrorsDto("password cannot be null or empty", "password"));
        }
        if (StringUtils.isBlank(dto.getRole().toString())) {
            errors.add(new ErrorsDto("role cannot be null or empty", "role"));
        }
        return errors;
    }
}
