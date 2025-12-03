package peaksoft.lms_springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.lms_springboot.entity.Instructor;
import peaksoft.lms_springboot.service.InstructorService;
@Controller
@RequestMapping("instructors")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;
    //todo get all
    @GetMapping
    public String getAllInstructors(Model model){
        model.addAttribute("allInstructors", instructorService.getAllInstructors());
        return "instructors";
    }

    //todo create
    @GetMapping("/newInst")
    //create
    public String createInstructor(Model model){
        model.addAttribute("newInstructor", new Instructor());
        return "newinstructor";
    }
    //save
    @PostMapping("/saveInst")
    public String saveInstructor(@ModelAttribute("newInstructor") Instructor instructor){
        instructorService.saveInstructor(instructor);
        return "redirect:/instructors";
    }

    //todo update
    //get by id
    @GetMapping("/{idInst}")
    public String getInstructor(@PathVariable("idInst") Long idInstructor, Model model){
        model.addAttribute("getInstructor", instructorService.getInstructorById(idInstructor));
        return "editInstructor";
    }
    //save edit
    @PostMapping("/{idInst}/updateInst")
    public String updateInstructor(@PathVariable("idInst") Long idInst, @ModelAttribute("getInstructor") Instructor instructor){
        instructorService.updateInstructor(idInst, instructor);
        return "redirect:/instructors";
    }

    //todo delete
    @GetMapping("/{idInst}/deleteInst")
    public  String deleteInstructor(@PathVariable("idInst") Long idInst, Model model){
        instructorService.deleteInstructor(idInst);
        return "redirect:/instructors";
    }

}
