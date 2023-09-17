package com.example.class_studen.model;

import com.example.class_studen.dto.UserDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(timeToLive = 1000*60*20)
public class UserAccess {
    @Id
    private String sessionId;
    private UserDto userDto;
}
