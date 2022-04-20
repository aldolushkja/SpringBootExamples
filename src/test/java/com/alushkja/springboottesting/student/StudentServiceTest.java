package com.alushkja.springboottesting.student;

import com.alushkja.springboottesting.student.dto.StudentDto;
import com.alushkja.springboottesting.student.exception.BadStudentRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
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
    void willThrowWhenEmailIsTaken() {
        var dto = StudentDto.buildDefault();

//        given(studentRepository.existByEmail(dto.email())).willReturn(true);
        given(studentRepository.existByEmail(anyString())).willReturn(true);

        assertThatThrownBy(() -> underTest.addStudent(dto))
                .isInstanceOf(BadStudentRequestException.class)
                .hasMessageContaining("Student with email " + dto.email() + " already exists!");

        verify(studentRepository, never()).saveAndFlush(any());
    }


    @Test
    @Disabled
    void canFindOneStudentById() {
        var dto = StudentDto.buildDefault();

        //FIXME: this doesn't work
        final var student = underTest.addStudent(dto);
        final var ut = student.getId();

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).existByEmail(dto.email());
        verify(studentRepository).saveAndFlush(studentArgumentCaptor.capture());

        underTest.findOneById(ut);

        verify(studentRepository).existsById(ut);
        final var retrieved = verify(studentRepository).findById(ut);

        retrieved.ifPresent(curr -> assertThat(curr.getId()).isEqualTo(ut));


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