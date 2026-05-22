package com.munna.relationshipapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.relationshipapi.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
