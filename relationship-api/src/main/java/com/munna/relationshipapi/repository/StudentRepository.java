package com.munna.relationshipapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.relationshipapi.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
