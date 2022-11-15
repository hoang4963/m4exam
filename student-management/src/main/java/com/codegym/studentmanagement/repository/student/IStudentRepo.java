package com.codegym.studentmanagement.repository.student;

import com.codegym.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepo extends JpaRepository<Student, Long> {
    @Query(nativeQuery = true,value = "select * from student where name like :name")
    Iterable<Student> searchAllByName(@Param("name") String name);
}
