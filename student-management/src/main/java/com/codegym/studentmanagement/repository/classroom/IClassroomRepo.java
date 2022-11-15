package com.codegym.studentmanagement.repository.classroom;

import com.codegym.studentmanagement.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassroomRepo extends JpaRepository<Classroom, Long> {
}
