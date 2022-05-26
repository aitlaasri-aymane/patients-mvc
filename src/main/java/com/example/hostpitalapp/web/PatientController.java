package com.example.hostpitalapp.web;

import com.example.hostpitalapp.entities.Patient;
import com.example.hostpitalapp.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "1") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword
                           ){
        if (page < 1){
            page = 1;
        } else if (size < 5)
            size = 5;
        Page<Patient> pagePatients = patientRepository.findByNameContains(keyword, PageRequest.of(page-1,size));
        model.addAttribute("patientList",pagePatients.getContent());
        model.addAttribute("keyword",keyword);
        model.addAttribute("size",size);
        model.addAttribute("patientTotalPages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("patientsTotalPages",pagePatients.getTotalPages());
        model.addAttribute("patientResultsPerPage",pagePatients.getSize());
        model.addAttribute("patientCurrentPage",pagePatients.getNumber());
        return "patients";
    }
    @GetMapping("/user/patients")
    @ResponseBody
    List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }
    @GetMapping("/")
    String home(){
        return "redirect:/index";
    }

    @RequestMapping(value="/admin/deletepatient", method = RequestMethod.DELETE)
    String deletePatient(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size,
            @RequestParam(name = "keyword") String keyword
    ){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+ page +"&keyword="+ keyword +"&size=" + size;
    }
    @GetMapping("/admin/addpatientform")
    String addPatientForm(Model model){
        model.addAttribute("patient", new Patient());
        return "addpatientform";
    }
    @PostMapping(path = "/admin/save")
    String savePatient(
            Model model,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @Valid Patient patient,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors() && patient.getId() == null)
            return "addpatientform";
        else if (bindingResult.hasErrors() && patient.getId() != null)
            return "editpatientform";
        patientRepository.save(patient);
        System.out.println(patient);
        return "redirect:/index?page="+ page +"&keyword="+ keyword +"&size=" + size;
    }
    @GetMapping( "/admin/editpatientform")
    String editPatient(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size,
            @RequestParam(name = "keyword") String keyword,
            Model model
    ){
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null)
            throw new RuntimeException("Patient cannot be found!");
        model.addAttribute("patient", patient);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);
        return "editpatientform";
    }
}
