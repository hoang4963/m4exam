package com.codegym.studentmanagement.controller.student;


import com.codegym.studentmanagement.model.Classroom;
import com.codegym.studentmanagement.model.DTO.StudentDTO;
import com.codegym.studentmanagement.model.Student;
import com.codegym.studentmanagement.service.classroom.IClassroomService;
import com.codegym.studentmanagement.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    IStudentService studentService;

    @Autowired
    IClassroomService classroomService;

    @PostMapping("/save")
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDTO){
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setAddress(studentDTO.getAddress());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setEmail(studentDTO.getEmail());

        Classroom classroom = classroomService.findById(studentDTO.getClassroomId()).get();
        student.setClassroom(classroom);
        studentService.save(student);
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }
    @GetMapping("/lists")
    public ResponseEntity<Iterable<Student>> allStudents(){
        return new ResponseEntity<>(studentService.findAll(),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        Optional<Student> studentOptional = studentService.findById(id);
        if (!studentOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.remove(id);
        return new ResponseEntity<>(studentOptional.get(),HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
        Optional<Student> studentOptional = studentService.findById(id);
        if (!studentOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Student student = new Student();
        student.setId(id);
        student.setName(studentDTO.getName());
        student.setAddress(studentDTO.getAddress());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setEmail(studentDTO.getEmail());

        Classroom classroom = classroomService.findById(studentDTO.getClassroomId()).get();
        student.setClassroom(classroom);
        studentService.save(student);
        return new ResponseEntity<>(studentService.save(student),HttpStatus.OK);
    }
    @GetMapping("/{name}")
    public ResponseEntity<Iterable<Student>> searchByName(@PathVariable String name){
        name = "%" + name +"%";
        return new ResponseEntity<>(studentService.searchByName(name),HttpStatus.OK);
    }
}
