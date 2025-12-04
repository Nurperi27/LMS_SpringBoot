package peaksoft.lms_springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.lms_springboot.entity.Course;
import peaksoft.lms_springboot.entity.Lesson;
import peaksoft.lms_springboot.entity.Student;
import peaksoft.lms_springboot.service.CourseService;
import peaksoft.lms_springboot.service.LessonService;
import peaksoft.lms_springboot.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    private final LessonService lessonService;

    //todo get all
    @GetMapping
    public String getAllStudents(Model model){
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("allStudents", studentService.getAllStudents());
       // model.addAttribute("allCourses", courseService.getAllCourses()); //for assign
        //досупные курсы для каждого студента
        Map<Long, List<Course>> courseMap = new HashMap<>();
        for(Student s : students){
            List<Course> available = courseService.getAvailableCoursesForStudent(s.getIdStudent());
            courseMap.put(s.getIdStudent(), available);
        }
        model.addAttribute("availableCourse", courseMap);
        return "students";
    }

    //todo create
    //add
    @GetMapping("/newSt")
    public String createStudent(Model model){
        model.addAttribute("newStudent", new Student());
        return "newstudent";
    }
    //save
    @PostMapping("/saveSt")
    public String saveStudent(@ModelAttribute("newStudent") Student student){
        studentService.addStudent(student);
        return "redirect:/students";
    }

    //todo update
    //get by id
    @GetMapping("/{idSt}")
    public String getStudent(@PathVariable("idSt") Long idSt, Model model){
        model.addAttribute("getStudent", studentService.getStudentById(idSt));
        return "editStudent";
    }
    //save edit
    @PostMapping("/{idSt}/updateSt")
    public String updateStudent(@PathVariable("idSt") Long idSt, @ModelAttribute("getStudent") Student student){
        studentService.updateStudent(idSt, student);
        return "redirect:/students";
    }

    //todo delete
    @GetMapping("/{idSt}/deleteSt")
    public String deleteStudent(@PathVariable("idSt") Long idSt){
        studentService.deleteStudent(idSt);
        return "redirect:/students";
    }

    //todo assign student to COURSE
    //get all students and all courses
    @GetMapping("/assignSt")
    public String assignStudent(Model model){
        model.addAttribute("studentList", studentService.getAllStudents());
        model.addAttribute("allCourses", courseService.getAllCourses());
        return "students";
    }
    //save
    @PostMapping("/assignSt/saveCourses")
    public String saveAssignedStudent(@RequestParam("idStudents") Long idStudents, @RequestParam("idCourse") Long idCourse){
        courseService.addStudentToCourse(idStudents, idCourse);
        return "redirect:/students";
    }

}
