package com.codegym.controller;

import com.codegym.model.Student;
import com.codegym.model.StudentForm;
import com.codegym.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Value("${file-upload}")
    private String fileUpload;
    @GetMapping ("")
    public String showAll(Model model){
       List<Student> students= studentService.findAll();
       model.addAttribute("students",students);
       return "/list";
    }
    @GetMapping("/create")
    public String showCreate(Model model){
        model.addAttribute("student",new StudentForm());
        return "/create";
    }
    @PostMapping("/create")
    public ModelAndView saveCreate(@ModelAttribute StudentForm studentForm){
        MultipartFile multipartFile = studentForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(studentForm.getImage().getBytes(),new File(fileUpload+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Student student = new Student(studentForm.getId(),studentForm.getName(),studentForm.getAddress(),studentForm.getEmail(),fileName);
        studentService.save(student);
        ModelAndView modelAndView=new ModelAndView("/create");
        modelAndView.addObject("student",student);
        return modelAndView;
    }
    @GetMapping("/{id}/delete")
    public String ShowDelete(@PathVariable Long id,Model model){
        model.addAttribute("student",studentService.findById(id));
        return "/delete";
    }
    @PostMapping("/{id}/delete")
    public String delete(@ModelAttribute("student") Student student){
        studentService.remove(student.getId());
        return "redirect:/student";
    }
    @GetMapping("/{id}/update")
    public String edit(@PathVariable Long id,Model model){
        model.addAttribute("student",studentService.findById(id));
        return "/edit";
    }
}
