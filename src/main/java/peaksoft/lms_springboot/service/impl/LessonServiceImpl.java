package peaksoft.lms_springboot.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.lms_springboot.entity.Course;
import peaksoft.lms_springboot.entity.Lesson;
import peaksoft.lms_springboot.repository.CourseRepository;
import peaksoft.lms_springboot.repository.LessonRepository;
import peaksoft.lms_springboot.service.LessonService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    @Override
    public void addLessonToCourseById(Long idCourse, Lesson lesson) {
        Course course1 = courseRepository.findById(idCourse).orElseThrow(() -> new RuntimeException("Such Course not found"));
        Lesson lesson1 = new Lesson();
        lesson1.setTitleLesson(lesson.getTitleLesson());
        lesson1.setPublisherDate(lesson.getPublisherDate());
        lesson1.setDescriptionLesson(lesson.getDescriptionLesson());
        lesson1.getCourses().add(course1);
        course1.getLessons().add(lesson1);
        lessonRepository.save(lesson1);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    @Override
    public List<Lesson> getAllLessonsByCourseId(Long idCourse) { //!!!!
        return lessonRepository.findAllLessonsByCourseId(idCourse);
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        Lesson lesson1 = lessonRepository.findById(id).orElseThrow(() -> new RuntimeException("Lesson not found"));
        lesson1.setTitleLesson(lesson.getTitleLesson());
        lesson1.setPublisherDate(lesson.getPublisherDate());
        lesson1.setDescriptionLesson(lesson.getDescriptionLesson());
        lessonRepository.save(lesson1);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }
}
