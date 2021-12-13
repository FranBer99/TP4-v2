package ar.edu.unnoba.poo2021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unnoba.poo2021.model.entity.Intervencion;
import ar.edu.unnoba.poo2021.model.service.IntervencionService;

@Controller
@RequestMapping("/intervenciones")
public class IntervencionController {
	
	@Autowired
    private IntervencionService intervencionService;

	@GetMapping("/vista_intervenciones")
    public String listaIntervenciones(Model model){
        model.addAttribute("intervenciones",intervencionService.getIntervencionesOrdenadas());
	    return "intervenciones/vista_intervenciones";
    }
	
	@GetMapping("/reg_intervencion")
    public String regIntervencionForm(Model model){
        model.addAttribute("intervencion", new Intervencion());
        return"intervenciones/reg_intervencion";
    }
    @PostMapping("/reg_intervencion")
    public String regIntervencion(@ModelAttribute Intervencion intervencion, Model model, RedirectAttributes redirectAttributes){
        try{
            model.addAttribute("intervencion", intervencionService.registrar(intervencion));
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/intervenciones/reg_intervencion"; }
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
