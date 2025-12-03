package peaksoft.lms_springboot.service;

import peaksoft.lms_springboot.entity.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    void updateStudent(Long id, Student student);
    void deleteStudent(Long id);
    Student findStudentByEmail(String email);
}
