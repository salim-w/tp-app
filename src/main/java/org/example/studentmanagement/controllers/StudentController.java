package org.example.studentmanagement.controllers;
import org.example.studentmanagement.services.StudentServices;
import org.example.studentmanagement.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
@RequestMapping("api")
public class StudentController {
    @Autowired
    private StudentServices studentServices;
    @Operation(summary = "Récupérer tous les étudiants")
    @GetMapping("/students")
    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = studentServices.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Save a new student
    @PostMapping("/students/save")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student savedStudent = studentServices.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }
    // Delete a student by ID
    @DeleteMapping("/students/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean deleted = studentServices.delete(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Count the number of students
    @GetMapping("/students/count")
    public ResponseEntity<Long> countStudent () {
        long count = studentServices.countStudents();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    // Get the number of students by year of birth
    @GetMapping("/students/byYear")
    public ResponseEntity<Collection<?>> findByYear() {
        Collection<?> studentsByYear = studentServices.findNbrStudentByYear();
        return new ResponseEntity<>(studentsByYear, HttpStatus.OK);

    }}