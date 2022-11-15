package com.codegym.studentmanagement.service.student;

import com.codegym.studentmanagement.model.Student;
import com.codegym.studentmanagement.service.IGeneralService;

public interface IStudentService extends IGeneralService<Student> {
    public Iterable<Student> searchByName(String name);
}
