package peaksoft.lms_springboot.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.lms_springboot.entity.Course;
import peaksoft.lms_springboot.entity.Instructor;
import peaksoft.lms_springboot.entity.Student;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    //    void saveCourse(Course course);
    //    Course getCourseById(Long id);
             //Course findById(long id);
    //    List<Course> getAllCourses();
    //    void updateCourse(Long id, Course course);
    //    void deleteCourseById(Long id);
    @Modifying //по умолчанию @Query - select запрос, а @Modifying - не select, а UPDATE/INSERT/DELETE
    @Transactional
    @Query(value = "insert into students_courses (courses_id, students_id) values(:courseId, :studentId)", nativeQuery = true)
    void addStudentToCourse(@Param("courseId") Long courseId, @Param("studentId") Long studentId);
            //@Param - связывает java-параметров метода с параметрами в SQL/HQL внутри @Query. Используется когда несколько параметров в методе
            //@Param("courseId") → привязывает courseId к :courseId
            //@Param("studentId") → привязывает studentId к :studentId

    @Query("select s from Student s join s.courses c where c.idCourse = :courseId")
    List<Student> getAllStudentsByCourse(Long courseId); //получить студентов определенного кусра

    @Query("select i from Instructor i join i.courses c where c.idCourse = :courseId")
    List<Instructor> getInstructorsByCourse(Long courseId); //получить учителей определенного кусра

    @Query("select c from Course c join c.students s where s.idStudent = :studentId")
    List<Course> getAllCoursesByStudentId(Long studentId);

    @Query("select c from Course c join c.instructors i where i.idInst = :instructorId")
    List<Course> getAllCoursesByInstructor(Long instructorId); //получить курсы определенного учителя

    @Query("select c from Course c where :student not member of c.students")
    List<Course> findCoursesNotAssignedToStudent(@Param("student")Student student);
}
