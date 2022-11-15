package com.codegym.studentmanagement.service.student;

import com.codegym.studentmanagement.model.Student;
import com.codegym.studentmanagement.repository.student.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StudentService implements IStudentService {

    @Autowired
    IStudentRepo studentRepo;


    @Override
    public Iterable<Student> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepo.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public void remove(Long id) {
        studentRepo.deleteById(id);
    }

    @Override
    public Iterable<Student> searchByName(String name) {
        return studentRepo.searchAllByName(name);
    }
}
