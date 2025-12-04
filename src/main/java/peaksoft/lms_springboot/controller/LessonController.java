package peaksoft.lms_springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.lms_springboot.entity.Course;
import peaksoft.lms_springboot.entity.Lesson;
import peaksoft.lms_springboot.service.CourseService;
import peaksoft.lms_springboot.service.LessonService;

import java.util.List;

@Controller
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private final CourseService courseService;
    /*//todo get all
    @GetMapping("/courses/{idC}/lessons")
    public String getAllLessonByCourseId(@PathVariable("idC") Long idC, Model model){
        model.addAttribute("allLessons", lessonService.getAllLessonsByCourseId(idC));
        return "lessons";
    }*/

    //todo create
    //add
    @GetMapping("/newL")
    public String createLesson(Model model){
        model.addAttribute("newLesson", new Lesson());
        model.addAttribute("bycourses", courseService.getAllCourses()); //for to choose course
        return "newlesson";
    }
    //save
    @PostMapping("/saveL")
    public String saveLesson(@RequestParam("idCourse") Long idCourse, @ModelAttribute("newLesson") Lesson lesson){
        lessonService.addLessonToCourseById(idCourse, lesson);
        return "redirect:/courses/" + idCourse + "/lessons";
    }

    //todo get LESSONS of course By Student
    //get lessons
    @GetMapping("/courses/{idCourse}/stLesson")
    public String getLessonsOfCourse(@PathVariable("idCourse") Long idCourse, Model model){
        Course course = courseService.getCourseById(idCourse);
        List<Lesson> lessonL = lessonService.getAllLessonsByCourseId(idCourse);
        model.addAttribute("course", course); //for button Enter
        model.addAttribute("allLessons", lessonL); //list of Lessons
        return "studentLesson";
    }
}
