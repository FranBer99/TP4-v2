package ar.edu.unnoba.poo2021.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ar.edu.unnoba.poo2021.model.entity.Intervencion;
import ar.edu.unnoba.poo2021.model.service.IntervencionService;
import ar.edu.unnoba.poo2021.model.service.ProfesionalService;
import ar.edu.unnoba.poo2021.model.service.QuirofanoService;

@Controller
@RequestMapping("/intervenciones")
public class IntervencionController {
	
	@Autowired
    private IntervencionService intervencionService;
	@Autowired
    private QuirofanoService quirofanoService;
	@Autowired
    private ProfesionalService profesionalService;

	@GetMapping("/vista_intervenciones")
    public String listaIntervenciones(Model model, @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFiltro){
		if(fechaFiltro == null) {
			model.addAttribute("intervenciones",intervencionService.getIntervencionesOrdenadas());
		}
		else {
			model.addAttribute("intervenciones",intervencionService.getIntervencionesFiltradas(fechaFiltro));
		}
	    return "intervenciones/vista_intervenciones";
    }
	
	@GetMapping("/matriz_intervenciones")
    public String matrizIntervenciones(Model model, @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFiltro){
		if(fechaFiltro == null) {
			model.addAttribute("mquirofanos",new ArrayList<>());
		}
		else {
			model.addAttribute("mquirofanos",quirofanoService.getMatriz(fechaFiltro));
		}
	    return "intervenciones/matriz_intervenciones";
    }
	
	@GetMapping("/reg_intervencion")
    public String regIntervencionForm(Model model){
        model.addAttribute("intervencion", new Intervencion());
        model.addAttribute("profesionales", profesionalService.getProfesionales());
        model.addAttribute("quirofanos", quirofanoService.getQuirofanos());
        return"intervenciones/reg_intervencion";
    }
	
    @PostMapping("/reg_intervencion")
    public String regIntervencion(@ModelAttribute Intervencion intervencion, Model model, RedirectAttributes redirectAttributes){
        try{
            model.addAttribute("intervencion", intervencionService.registrar(intervencion, intervencion.getQuirofano()));
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
    
    @GetMapping("/vista_intervencion/{id}")
    public String vistaIntervencion(@PathVariable("id") Long intervencionId, Model model){
        Intervencion intervencion  = intervencionService.findById(intervencionId);
        model.addAttribute("intervencion",intervencion);
        return "intervenciones/vista_intervencion";
    }
}
