package com.alushkja.springboottesting.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentExistByName() {
        //given
        final var student = Student.buildDefault();
        final var name = student.getName();
        underTest.saveAndFlush(student);

        //when
        final var expected = underTest.existByName(name);

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfStudentExistByEmail() {
        var student = Student.buildDefault();
        final var email = student.getEmail();
        underTest.saveAndFlush(student);

        final var expected = underTest.existByEmail(email);

        assertThat(expected).isTrue();
    }


    @Test
    void itShouldFindStudentByEmail() {
        var student = Student.buildDefault();
        final var email = student.getEmail();
        underTest.saveAndFlush(student);

        final var expected = underTest.findByEmail(email).get(0);

        assertThat(expected).isNotNull();
    }


    @Test
    void itShouldFindStudentByName() {
        var student = Student.buildDefault();
        final var name = student.getName();
        underTest.saveAndFlush(student);

        final var expected = underTest.findByName(name).get(0);

        assertThat(expected).isNotNull();
    }
}