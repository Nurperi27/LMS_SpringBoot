package peaksoft.lms_springboot.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.lms_springboot.entity.Course;
import peaksoft.lms_springboot.entity.Instructor;
import peaksoft.lms_springboot.entity.Student;
import peaksoft.lms_springboot.repository.CourseRepository;
import peaksoft.lms_springboot.repository.StudentRepository;
import peaksoft.lms_springboot.service.CourseService;
import peaksoft.lms_springboot.service.StudentService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(()->new NoSuchElementException("Such " + id + " id not found"));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void updateCourse(Long id, Course course) {
        Course course1 = courseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Such " + id + " id not found"));
        course1.setTitleCourse(course.getTitleCourse());
        course1.setImage(course.getImage());
        course1.setStartDate(course.getStartDate());
        course1.setDescription(course.getDescription());
        courseRepository.save(course1);
    }

    @Override
    public void deleteCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Such " + id + " id not found"));
        //удаляем курс(без удаления instructors)
        for (Instructor instructor : course.getInstructors()) {
            instructor.getCourses().remove(course);
        }
        course.getInstructors().clear();
        courseRepository.delete(course);
    }

    @Override
    public void addStudentToCourse(Long idStudents, Long courseId) {
//        Student student1 = studentRepository.findById(idStudents).orElseThrow(() -> new NoSuchElementException("Such " + idStudents + " id not found"));
//        Course course1 = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Such " + courseId + " id not found"));
//        course1.getStudents().add(student1);
//        student1.getCourses().add(course1);
//        courseRepository.save(course1);

        courseRepository.addStudentToCourse(courseId, idStudents);
    }

    @Override
    public List<Student> getAllStudentsByCourse(Long courseId) {
        return courseRepository.getAllStudentsByCourse(courseId);
    }

    @Override
    public List<Instructor> getInstructorsByCourse(Long courseId) {
        return courseRepository.getInstructorsByCourse(courseId);
    }

    @Override
    public List<Course> getAllCoursesByStudentId(Long studentId) {
        return courseRepository.getAllCoursesByStudentId(studentId);
    }

    @Override
    public List<Course> getAllCoursesByInstructor(Long instructorId) {
        return courseRepository.getAllCoursesByInstructor(instructorId);
    }

    @Override
    public List<Course> getAvailableCoursesForStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Such " + studentId + " id not found"));
        return courseRepository.findCoursesNotAssignedToStudent(student);
    }

}
