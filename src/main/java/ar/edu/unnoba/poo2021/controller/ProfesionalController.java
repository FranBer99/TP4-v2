package ar.edu.unnoba.poo2021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ar.edu.unnoba.poo2021.model.entity.Profesional;
import ar.edu.unnoba.poo2021.model.service.ProfesionalService;

@Controller
@RequestMapping("/profesionales")
public class ProfesionalController {
	
	@Autowired
    private ProfesionalService profesionalService;

	@GetMapping("/lista_profesionales")
    public String listaProfesionales(Model model){
		model.addAttribute("profesionales",profesionalService.getProfesionales());
	    return "profesionales/lista_profesionales";
    }
	
	@GetMapping("/reg_profesional")
    public String regProfesionalForm(Model model){
        model.addAttribute("profesional", new Profesional());
        return"profesionales/reg_profesional";
    }
	
    @PostMapping("/reg_profesional")
    public String regProfesional(@ModelAttribute Profesional profesional, Model model, RedirectAttributes redirectAttributes){
        try{
            model.addAttribute("profesional", profesionalService.registrar(profesional));
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/profesionales/reg_profesional";
            }
        return "redirect:/profesionales/lista_profesionales";
    }

    @GetMapping("/borrar/{id}")
    public String profesionalDelete(@PathVariable("id") Long profesionalId, RedirectAttributes redirectAttributes){
    	profesionalService.delete(profesionalId);
        return "redirect:/profesionales/lista_profesionales";
    }
}

