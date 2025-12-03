package peaksoft.lms_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.lms_springboot.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByEmail(String email);

    //    void addStudent(Student student);
    //    Student getStudentById(Long id);
    //    List<Student> getAllStudents();
    //    void updateStudent(Long id, Student student);
    //    void deleteStudent(Long id);
    //    Student findStudentByEmail(String email);
}
