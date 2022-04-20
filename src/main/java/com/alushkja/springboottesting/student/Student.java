package com.alushkja.springboottesting.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public static Student buildDefault(){
        final var studentUUID = UUID.randomUUID().toString();
        var student = new Student();
        student.setName("custom student-"+ studentUUID);
        student.setEmail("custom" + studentUUID + "@email.it");
        student.setGender((Math.random() * 10 ) > 10 ? Gender.MALE : Gender.FEMALE );
        return student;
    }

}
