package com.example.class_studen.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeachersDto {

    private Integer id;
    private String firstname;
    private String lastname;

    private Set<SubjTeachDto> subjTeach;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
