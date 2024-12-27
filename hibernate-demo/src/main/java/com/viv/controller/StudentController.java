package com.viv.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.viv.entity.Student;
import com.viv.service.StudentService;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

        @Autowired
        private StudentService studentService;

        @GetMapping
        public List<Student> getAllStudents() {
            return studentService.getAllStudents();
        }

        @GetMapping("/{id}")
        public Student getStudentById(@PathVariable Long id) {
            return studentService.getStudentById(id);
        }

        @PostMapping
        public Student createStudent(@RequestBody Student student) {
            
            return studentService.createStudent(student);
        }

        @PutMapping("/{id}")
        public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
            return studentService.updateStudent(id, student);
        }

        @DeleteMapping("/{id}")
        public void deleteStudent(@PathVariable Long id) {
            studentService.deleteStudent(id);
        }
}

