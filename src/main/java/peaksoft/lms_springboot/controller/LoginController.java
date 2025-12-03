package peaksoft.lms_springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import peaksoft.lms_springboot.entity.Course;
import peaksoft.lms_springboot.entity.Instructor;
import peaksoft.lms_springboot.entity.Student;
import peaksoft.lms_springboot.service.CourseService;
import peaksoft.lms_springboot.service.InstructorService;
import peaksoft.lms_springboot.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final InstructorService instructorService;
    private final CourseService courseService;
    private final StudentService studentService;
    @GetMapping
    public String loginPage() {
        return "login";
    }
    @PostMapping("/check") //если бы в @GetMapping("/login"), то ->@PostMapping("/login/check")
    public String checkLogin(@RequestParam("email") String email, Model model) {
        //check Instructor
        Instructor instructor = instructorService.findInstructorByEmail(email);
//        if (instructor == null) {
//            return "login";
//        } else { //if (instructor != null)
//            return  "redirect:/courses";
//        }
        if (instructor != null) {
            model.addAttribute("instructor", instructor);
            List<Course> courses = courseService.getAllCoursesByInstructor(instructor.getIdInst());
            model.addAttribute("courses", courses);
            return "loginInstructor";
        }

        //check Student
        Student student = studentService.findStudentByEmail(email);
        if(student != null){
            model.addAttribute("student",student);
            model.addAttribute("courseS",courseService.getAllCoursesByStudentId(student.getIdStudent()));
            return "loginStudent";
        }
        model.addAttribute("error","Email not found");
        return "login";
    }
}
