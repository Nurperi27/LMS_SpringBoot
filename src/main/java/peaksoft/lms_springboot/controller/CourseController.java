package peaksoft.lms_springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.lms_springboot.entity.Course;
import peaksoft.lms_springboot.entity.Instructor;
import peaksoft.lms_springboot.entity.Lesson;
import peaksoft.lms_springboot.entity.Student;
import peaksoft.lms_springboot.service.CourseService;
import peaksoft.lms_springboot.service.InstructorService;
import peaksoft.lms_springboot.service.LessonService;

import java.util.List;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final LessonService lessonService;
    private final InstructorService instructorService;

    //todo get all
    @GetMapping
    public String getAllCourses(Model model){
        model.addAttribute("allCourses", courseService.getAllCourses());
        model.addAttribute("allInstructors", instructorService.getAllInstructors());
        return "courses";
    }

    //todo create
    //add
    @GetMapping("/newC")
    public String addCourse(Model model){
        model.addAttribute("newCourse", new Course());
        return "newcourse";
    }
    //save
    @PostMapping("/saveC")
    public String saveCourse(@ModelAttribute("newCourse") Course course){
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    //todo update
    //get by id
    @GetMapping("/{id}")
    public String getCourseById(@PathVariable("id") Long id, Model model){
        model.addAttribute("getCourse", courseService.getCourseById(id));
        return "editCourse";
    }
    //save edit
    @PostMapping("/{id}/updateC")
    public String updateCourse(@PathVariable("id") Long id, @ModelAttribute("getCourse") Course course){
        courseService.updateCourse(id, course);
        return "redirect:/courses";
    }

    //todo delete
    @GetMapping("/{id}/deleteC")
    public String deleteCourse(@PathVariable("id") Long id){
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

    //todo get LESSONS of course
    //get lessons
    @GetMapping("/{idCourse}/lessons")
    public String getLessonsOfCourse(@PathVariable("idCourse") Long idCourse, Model model){
        Course course = courseService.getCourseById(idCourse);
        List<Lesson> lessonL = lessonService.getAllLessonsByCourseId(idCourse);
        model.addAttribute("course", course); //for ADD LESSON button
        model.addAttribute("allLessons", lessonL); //list of Lessons
        return "lessons";
    }

    //todo create LESSON
    //add
    @GetMapping("/{idCourse}/addNewLesson")
    public String createLesson(@PathVariable("idCourse") Long  idCourse, Model model){
        model.addAttribute("newLesson", new Lesson());
        model.addAttribute("courseId", idCourse);
        return "newlesson";
    }

    //todo update LESSON
    //get lesson by course-id and by lesson-id
    @GetMapping("/{courseId}/lessons/{lessonId}")
    public String getLesson(@PathVariable("courseId") Long courseId, @PathVariable("lessonId") Long lessonId, Model model){
        model.addAttribute("getLesson", lessonService.getLessonById(lessonId));
        model.addAttribute("courseId", courseId);
        return "editLesson";
    }
    //save update
    @PostMapping("/{courseId}/lessons/{lessonId}/updateLesson")
    public String updateLesson(@PathVariable("courseId") Long courseId, @PathVariable("lessonId") Long lessonId, @ModelAttribute("getLesson") Lesson lesson){
        lessonService.updateLesson(lessonId, lesson);
        return "redirect:/courses/" + courseId + "/lessons";
    }

    //todo delete LESSON from course
    @GetMapping("{courseId}/lessons/{lessonId}/deleteLesson")
    public String deleteLesson(@PathVariable("courseId") Long courseId, @PathVariable("lessonId") Long lessonId){
        lessonService.deleteLesson(lessonId);
        return "redirect:/courses/" + courseId + "/lessons";
    }

    //todo show assigned STUDENTS of course
    @GetMapping("/{id}/students")
    public String showStudents(@PathVariable("id") Long id, Model model){
        Course course = courseService.getCourseById(id);
        List<Student> sC = courseService.getAllStudentsByCourse(id);
        model.addAttribute("course", course);
        model.addAttribute("studentsCourse", sC);
        return "assignedStudents";
    }

    //todo assign INSTRUCTOR to course
    @PostMapping("/{courseId}/assignInst")
    public String assignInstructorToCourse(@PathVariable("courseId") Long courseId, @RequestParam("instructorId") List<Long> instructorId){
        for(Long instId : instructorId){
            instructorService.assignInstructorToCourse(courseId, instId);
        }
        return "redirect:/courses/" + courseId + "/instructors";
    }

    //todo show assigned INSTRUCTORS
    @GetMapping("/{id}/instructors")
    public String showInstructors(@PathVariable("id") Long id, Model model){
        Course course = courseService.getCourseById(id);
        List<Instructor> iC = courseService.getInstructorsByCourse(id);
        model.addAttribute("course", course);
        model.addAttribute("instructorsCourse", iC);
        model.addAttribute("allInstructors", instructorService.getAllInstructors());
        return "assignedInstructor";
    }


}
