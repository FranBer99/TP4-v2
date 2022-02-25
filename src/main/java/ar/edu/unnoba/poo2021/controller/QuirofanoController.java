package ar.edu.unnoba.poo2021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ar.edu.unnoba.poo2021.model.entity.Quirofano;
import ar.edu.unnoba.poo2021.model.service.QuirofanoService;

@Controller
@RequestMapping("/quirofanos")
public class QuirofanoController {

    @Autowired
    private QuirofanoService quirofanoService;
    
    @GetMapping("/lista_quirofanos")
    public String listaQuirofanos(Model model){
        model.addAttribute("quirofanos", quirofanoService.getQuirofanos());
        return"quirofanos/lista_quirofanos";
    }

    @GetMapping("/reg_quirofano")
    public String regIntervencionForm(Model model){
        model.addAttribute("quirofano", new Quirofano());
        return"quirofanos/reg_quirofano";
    }

    @PostMapping("/reg_quirofano")
    public String regIntervencion(@ModelAttribute Quirofano quirofano, Model model){
        model.addAttribute("quirofano", quirofanoService.registrar(quirofano));
        return "redirect:/quirofanos/lista_quirofanos";
    }

    @GetMapping("/borrar/{id}")
    public String quirofanoDelete(@PathVariable("id") Long quirofanoId){
        quirofanoService.delete(quirofanoId);
        return "redirect:/quirofanos/lista_quirofanos";
    }
}