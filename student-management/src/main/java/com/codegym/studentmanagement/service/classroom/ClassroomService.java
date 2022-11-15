package com.codegym.studentmanagement.service.classroom;

import com.codegym.studentmanagement.model.Classroom;
import com.codegym.studentmanagement.repository.classroom.IClassroomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService implements IClassroomService{

    @Autowired
    IClassroomRepo classroomRepo;

    @Override
    public Iterable<Classroom> findAll() {
        return classroomRepo.findAll();
    }

    @Override
    public Optional<Classroom> findById(Long id) {
       return classroomRepo.findById(id);
    }

    @Override
    public Classroom save(Classroom classroom) {
        return classroomRepo.save(classroom);
    }

    @Override
    public void remove(Long id) {
            classroomRepo.deleteById(id);
    }
}
