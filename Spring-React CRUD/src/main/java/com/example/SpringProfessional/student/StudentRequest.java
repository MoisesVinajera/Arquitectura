package com.example.SpringProfessional.student;

import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    private String name;
    private Gender gender;
    private String email;
    private Integer age;
}
