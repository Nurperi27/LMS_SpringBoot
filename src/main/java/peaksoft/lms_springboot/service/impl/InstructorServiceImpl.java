package peaksoft.lms_springboot.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.lms_springboot.entity.Course;
import peaksoft.lms_springboot.entity.Instructor;
import peaksoft.lms_springboot.repository.CourseRepository;
import peaksoft.lms_springboot.repository.InstructorRepository;
import peaksoft.lms_springboot.service.InstructorService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    @Override
    public void saveInstructor(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id).orElseThrow(()->new NoSuchElementException("instructor not found"));
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        Instructor oldInstructor = instructorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("instructor not found"));
        oldInstructor.setFirstName(instructor.getFirstName());
        oldInstructor.setLastName(instructor.getLastName());
        oldInstructor.setEmail(instructor.getEmail());
        oldInstructor.setPhoneNumber(instructor.getPhoneNumber());
        instructorRepository.save(oldInstructor);
    }

    @Override
    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }

    @Override
    public void assignInstructorToCourse(Long courseId, Long instructorId) {
        instructorRepository.assignInstructorToCourse(courseId, instructorId);
    }

    @Override
    public Instructor findInstructorByEmail(String email) {
        return instructorRepository.findInstructorByEmail(email);
    }
}
