package peaksoft.lms_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.lms_springboot.entity.Lesson;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    //    void addLessonToCourseById(Long idCourse, Lesson lesson);
    //    Lesson getLessonById(Long id);

    //    List<Lesson> getAllLessonsByCourseId(Long idCourse); //получить список уроков определенного курса
    @Query("select l from Lesson l join l.courses c where c.idCourse = :idCourse")
    List<Lesson> findAllLessonsByCourseId(Long idCourse);

    //    void updateLesson(Long id, Lesson lesson);
    //    void deleteLesson(Long id);
}
