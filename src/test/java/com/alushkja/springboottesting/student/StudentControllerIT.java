package com.alushkja.springboottesting.student;

import com.alushkja.springboottesting.student.dto.StudentDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class StudentControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void itShouldSaveOneStudent() throws Exception {
        final var dto = StudentDto.buildDefault();
        var student = dto.toEntity();
        student.setId(1L);

        when(studentService.addStudent(dto)).thenReturn(student);

        mockMvc.perform(post("/api/v1/students"))
                .andDo(print())
                .andExpect(status().is(201));

        verify(studentService).addStudent(dto);
    }
}