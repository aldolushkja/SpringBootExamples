package com.alushkja.springboottesting.student.dto;

import com.alushkja.springboottesting.student.Gender;
import com.alushkja.springboottesting.student.Student;

import java.util.UUID;

public record StudentDto(String name, String email, String gender) {


    public static StudentDto buildDefault(){
        final var uuid = UUID.randomUUID().toString();
        return new StudentDto("name"+uuid, "email"+uuid+"@mail.it", "MALE");
    }

    public Student toEntity(){
        var student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setGender(Gender.valueOf(gender));
        return student;
    }
}
