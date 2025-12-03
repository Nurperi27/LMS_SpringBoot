package peaksoft.lms_springboot.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.lms_springboot.entity.Course;
import peaksoft.lms_springboot.entity.Instructor;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    Instructor findInstructorByEmail(String email);

    //    void saveInstructor(Instructor instructor);
    //    List<Instructor> getAllInstructors();
    //    Instructor getInstructorById(Long id);
    //    void updateInstructor(Long id, Instructor instructor);
    //    void deleteInstructor(Long id);

    @Modifying
    @Transactional
    @Query(value = "insert into courses_instructors (courses_id, instructors_id) values (:courseId, :instructorId)",  nativeQuery = true)
    void assignInstructorToCourse(@Param("courseId") Long courseId, @Param("instructorId") Long instructorId);


}
