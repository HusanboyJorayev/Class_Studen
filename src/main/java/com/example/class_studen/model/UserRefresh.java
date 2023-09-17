package com.example.class_studen.model;

import com.example.class_studen.dto.UserDto;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(timeToLive = 1000*60*60)
public class UserRefresh {
   @Id
    private String sessionId;
   private UserDto dto;
}
