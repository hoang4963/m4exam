package com.codegym.studentmanagement.controller.classroom;

import com.codegym.studentmanagement.model.Classroom;
import com.codegym.studentmanagement.model.Student;
import com.codegym.studentmanagement.service.classroom.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classroom")
@CrossOrigin("*")
public class ClassroomController {
    @Autowired
    IClassroomService classroomService;
    @GetMapping("/lists")
    public ResponseEntity<Iterable<Classroom>> allclassroom(){
        return new ResponseEntity<>(classroomService.findAll(), HttpStatus.OK);
    }
}
