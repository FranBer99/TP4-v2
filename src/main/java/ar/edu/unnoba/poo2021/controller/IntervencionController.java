package ar.edu.unnoba.poo2021.controller;

import ar.edu.unnoba.poo2021.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unnoba.poo2021.model.entity.Intervencion;
import ar.edu.unnoba.poo2021.model.entity.Quirofano;
import ar.edu.unnoba.poo2021.model.service.IntervencionService;
import ar.edu.unnoba.poo2021.model.service.QuirofanoService;

@Controller
@RequestMapping("/intervenciones")
public class IntervencionController {
	
	@Autowired
    private IntervencionService intervencionService;
	@Autowired
    private QuirofanoService quirofanoService;

	@GetMapping("/vista_intervenciones")
    public String listaIntervenciones(Model model){
        model.addAttribute("intervenciones",intervencionService.getIntervencionesOrdenadas());
	    return "intervenciones/vista_intervenciones";
    }
	
	@GetMapping("/reg_intervencion")
    public String regIntervencionForm(Model model){
        model.addAttribute("intervencion", new Intervencion());
        model.addAttribute("quirofanos", quirofanoService.getQuirofanos());
        return"intervenciones/reg_intervencion";
    }
    @PostMapping("/reg_intervencion")
    public String regIntervencion(@ModelAttribute Intervencion intervencion, @ModelAttribute Quirofano quirofano, Model model, RedirectAttributes redirectAttributes){
        try{
        	intervencion.setQuirofano(quirofano);
            model.addAttribute("intervencion", intervencionService.registrar(intervencion, quirofano));
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/intervenciones/reg_intervencion";
            }
        return "redirect:/intervenciones/vista_intervenciones";
    }

    @GetMapping("/borrar/{id}")
    public String userDelete(@PathVariable("id") Long interventionId,
                             RedirectAttributes redirectAttributes){
        intervencionService.delete(interventionId);
        return "redirect:/intervenciones/vista_intervenciones";
    }

    @GetMapping("/editar/{id}")
    public String userEdit(@PathVariable("id") Long intervencionId, Model model){
        Intervencion intervencion  = intervencionService.findById(intervencionId);
        model.addAttribute("intervencion",intervencion);
        return "intervenciones/editar";
    }

    @PostMapping("/editar")
    public String update(@ModelAttribute Intervencion intervencion){
        intervencionService.update(intervencion);
        return "redirect:/intervenciones/vista_intervenciones";
    }

}
