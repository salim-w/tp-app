package org.example.studentmanagement.repositories;
import org.example.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Collection;
    @Repository
    public interface StudentRepository extends JpaRepository<Student , Integer> {
        Student findById(int id);
        // Requête personnalisée pour compter les étudiants par année de naissance
        @Query("SELECT YEAR(s.dateNaissance), COUNT(s) FROM Student s GROUP BY YEAR(s.dateNaissance)")
        Collection<Object[]> findNbrStudentByYear();

    }

