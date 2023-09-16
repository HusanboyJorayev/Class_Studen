package com.example.class_studen.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subjects")
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;

    @OneToMany(mappedBy = "subjectId",fetch = FetchType.EAGER)
    private Set<Marks>marks;

    @OneToMany(mappedBy = "subjectId",fetch = FetchType.EAGER)
    private Set<SubTeach>subjTeach;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
