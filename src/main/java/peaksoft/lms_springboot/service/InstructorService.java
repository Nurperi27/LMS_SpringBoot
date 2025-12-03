package peaksoft.lms_springboot.service;

import peaksoft.lms_springboot.entity.Course;
import peaksoft.lms_springboot.entity.Instructor;

import java.util.List;

public interface InstructorService {
    void saveInstructor(Instructor instructor);
    List<Instructor> getAllInstructors();
    Instructor getInstructorById(Long id);
    void updateInstructor(Long id, Instructor instructor);
    void deleteInstructor(Long id);
    void assignInstructorToCourse(Long courseId, Long instructorId);
    Instructor findInstructorByEmail(String  email);
}
