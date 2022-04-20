package com.alushkja.springboottesting.student;

import com.alushkja.springboottesting.student.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> saveOne(@Valid @RequestBody StudentDto studentDto){
        return ResponseEntity.ok(studentService.addStudent(studentDto));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("find-by-email")
    public ResponseEntity<List<Student>> findByEmail(@RequestParam(name = "email") String email){
        return ResponseEntity.ok(studentService.findByEmail(email));
    }

    @GetMapping("find-by-name")
    public ResponseEntity<List<Student>> findByName(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(studentService.findByName(name));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOne(@PathVariable Long id){
        studentService.deleteById(id);
        return ResponseEntity.ok("Student with id " + id + " deleted");
    }

}
