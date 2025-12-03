package peaksoft.lms_springboot.service;

import peaksoft.lms_springboot.entity.Course;
import peaksoft.lms_springboot.entity.Instructor;
import peaksoft.lms_springboot.entity.Student;

import java.util.List;

public interface CourseService {
    void saveCourse(Course course);
    Course getCourseById(Long id);
    List<Course> getAllCourses();
    void updateCourse(Long id, Course course);
    void deleteCourseById(Long id);
    void addStudentToCourse(Long idStudents, Long courseId);
    List<Student> getAllStudentsByCourse(Long courseId);
    List<Instructor> getInstructorsByCourse(Long courseId);
    List<Course> getAllCoursesByStudentId(Long studentId);
    List<Course> getAllCoursesByInstructor(Long instructorId);
}
