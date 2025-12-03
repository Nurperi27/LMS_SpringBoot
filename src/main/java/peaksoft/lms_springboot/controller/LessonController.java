package peaksoft.lms_springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.lms_springboot.entity.Lesson;
import peaksoft.lms_springboot.service.CourseService;
import peaksoft.lms_springboot.service.LessonService;

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
}
