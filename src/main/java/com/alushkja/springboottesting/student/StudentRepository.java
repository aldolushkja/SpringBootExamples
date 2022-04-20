package com.alushkja.springboottesting.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END from Student s where s.name = :name")
    Boolean existByName(String name);

    @Query(value = "SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END from Student s where s.email = :email")
    boolean existByEmail(String email);

    List<Student> findByName(String name);
    List<Student> findByEmail(String email);
}
