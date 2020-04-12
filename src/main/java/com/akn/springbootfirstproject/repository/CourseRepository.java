package com.akn.springbootfirstproject.repository;

import com.akn.springbootfirstproject.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
