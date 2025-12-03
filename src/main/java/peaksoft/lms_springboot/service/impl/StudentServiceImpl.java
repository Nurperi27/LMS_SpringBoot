package peaksoft.lms_springboot.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.lms_springboot.entity.Lesson;
import peaksoft.lms_springboot.entity.Student;
import peaksoft.lms_springboot.repository.LessonRepository;
import peaksoft.lms_springboot.repository.StudentRepository;
import peaksoft.lms_springboot.service.StudentService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()->new RuntimeException("Such Student not found!"));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void updateStudent(Long id, Student student) {
        Student student1 = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Such Student not found!"));
        student1.setLastName(student.getLastName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        studentRepository.save(student1);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student findStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
}
