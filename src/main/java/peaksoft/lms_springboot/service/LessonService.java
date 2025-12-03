package peaksoft.lms_springboot.service;

import peaksoft.lms_springboot.entity.Lesson;

import java.util.List;

public interface LessonService {
    void addLessonToCourseById(Long idCourse, Lesson lesson);
    Lesson getLessonById(Long id);
    List<Lesson> getAllLessonsByCourseId(Long idCourse);
    void updateLesson(Long id, Lesson lesson);
    void deleteLesson(Long id);
}
