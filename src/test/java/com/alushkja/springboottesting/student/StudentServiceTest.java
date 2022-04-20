package com.alushkja.springboottesting.student;

import com.alushkja.springboottesting.student.dto.StudentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }


    @Test
    void canAddStudent() {
        var dto = StudentDto.buildDefault();
        underTest.addStudent(dto);
        final var student = dto.toEntity();

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).existByEmail(dto.email());
        verify(studentRepository).saveAndFlush(studentArgumentCaptor.capture());

        final var capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    @Disabled
    void findOneById() {
    }

    @Test
    @Disabled
    void findByName() {
    }

    @Test
    @Disabled
    void findByEmail() {
    }

    @Test
    void canFindAllStudents() {
        underTest.getAllStudents();
        verify(studentRepository).findAll();
    }

    @Test
    @Disabled
    void deleteById() {
    }
}