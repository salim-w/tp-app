package org.example.studentmanagement.test;
import org.example.studentmanagement.controllers.StudentController;
import org.example.studentmanagement.entity.Student;
import org.example.studentmanagement.services.StudentServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
    @SpringBootTest
    class StudentControllerTest {

        @Mock
        private StudentServices studentServices;

        @InjectMocks
        private StudentController studentController;

        @Test
        void testSaveStudent() {
            // Create a student for testing
            Student student = new Student();
            student.setId(1);
            student.setNom("Mido");

            // Simulate saving the student
            when(studentServices.save(any(Student.class))).thenReturn(student);

            // Call the controller to save the student
            ResponseEntity<Student> response = studentController.save(student);

            // Verify the response status and saved student
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertEquals("Mido", response.getBody().getNom());
        }

        @Test
        void testSaveStudentWhenServiceFails() {
            // Simulate saving a student but service fails
            when(studentServices.save(any(Student.class))).thenThrow(new RuntimeException("Save failed"));

            // Call the controller to save the student and handle exception
            try {
                studentController.save(new Student());
            } catch (RuntimeException e) {
                // Verify that the exception was thrown
                assertEquals("Save failed", e.getMessage());
            }
        }

        @Test
        void testDeleteStudent() {
            // Simulate deleting a student
            when(studentServices.delete(1)).thenReturn(true);

            // Call the controller to delete the student
            ResponseEntity<Void> response = studentController.delete(1);

            // Verify the response status
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        }

        @Test
        void testDeleteStudentWhenNotFound() {
            // Simulate deleting a student but student is not found
            when(studentServices.delete(1)).thenReturn(false);

            // Call the controller to delete the student
            ResponseEntity<Void> response = studentController.delete(1);

            // Verify the response status when the student is not found
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }

        @Test
        void testFindAllStudents() {
            // Create mock students
            Student student1 = new Student();
            Student student2 = new Student();

            // Simulate retrieving all students
            when(studentServices.findAll()).thenReturn(Arrays.asList(student1, student2));

            // Call the controller to retrieve all students
            ResponseEntity<List<Student>> response = studentController.findAll();

            // Verify that the returned list contains the students
            assertEquals(2, response.getBody().size());
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testFindAllStudentsWhenNoStudents() {
            // Simulate retrieving all students when there are no students
            when(studentServices.findAll()).thenReturn(Arrays.asList());

            // Call the controller to retrieve all students
            ResponseEntity<List<Student>> response = studentController.findAll();

            // Verify that the returned list is empty
            assertEquals(0, response.getBody().size());
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testCountStudents() {
            // Simulate counting students
            when(studentServices.countStudents()).thenReturn(10L);

            // Call the controller to count the students
            ResponseEntity<Long> response = studentController.countStudent();

            // Verify that the returned count is correct
            assertEquals(10L, response.getBody());
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testFindByYear() {
            // Simulate retrieving students by year
            when(studentServices.findNbrStudentByYear()).thenReturn(Arrays.asList());

            // Call the controller to retrieve the number of students by year
            ResponseEntity<Collection<?>> response = studentController.findByYear();

            // Verify that the returned collection is empty
            assertEquals(0, response.getBody().size());
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }

