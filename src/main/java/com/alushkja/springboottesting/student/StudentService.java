package com.alushkja.springboottesting.student;

import com.alushkja.springboottesting.student.dto.StudentDto;
import com.alushkja.springboottesting.student.exception.BadStudentRequestException;
import com.alushkja.springboottesting.student.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    final StudentRepository studentRepository;

    @Transactional
    public Student addStudent(StudentDto studentDto) {
        if(studentRepository.existByEmail(studentDto.email())){
            throw new BadStudentRequestException("Student with email " + studentDto.email() + " already exists!");
        }
        return studentRepository.saveAndFlush(studentDto.toEntity());
    }

    public Optional<Student> findOneById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }
        return studentRepository.findById(id);
    }

    public List<Student> findByName(String name) {
        if(!studentRepository.existByName(name)){
            throw new StudentNotFoundException("Student with name " + name + " not found");
        }
        return studentRepository.findByName(name);
    }

    public List<Student> findByEmail(String email) {
        if(!studentRepository.existByEmail(email)){
            throw new StudentNotFoundException("Student with email " + email + " not found");
        }
        return studentRepository.findByEmail(email);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }
        studentRepository.deleteById(id);
    }
}
