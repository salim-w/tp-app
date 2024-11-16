package org.example.studentmanagement.services;
import org.example.studentmanagement.repositories.StudentRepository;
import org.example.studentmanagement.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
    @Service
    public class StudentServices {
        @Autowired
        private StudentRepository studentRepository;
        public Student save(Student student) {
            return studentRepository.save(student); }
        public boolean delete(int id) {
            Optional<Student> student0ptional = Optional.ofNullable(studentRepository.findById(id));
            if (student0ptional.isPresent()) {
                studentRepository.delete(student0ptional.get());
                return true;
            } else {
                return false;
            }
        }

        public List<Student> findAll() {
            return studentRepository.findAll();
        }
        public long countStudents() {
            return studentRepository.count();
        }
        public Collection<?> findNbrStudentByYear() {
            return studentRepository.findNbrStudentByYear();
        }
}
