package ar.edu.unnoba.poo2021.controller;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

	@GetMapping("/admin/vista_intervenciones")
    public String listaIntervencionesAdmin(Model model, @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFiltro){
		if(fechaFiltro == null) {
			model.addAttribute("intervenciones",intervencionService.getIntervencionesOrdenadas());
		}
		else {
			model.addAttribute("intervenciones",intervencionService.getIntervencionesFiltradas(fechaFiltro));
		}
	    return "intervenciones/admin/vista_intervenciones";
    }
	
	@GetMapping("/admin/matriz_intervenciones")
    public String matrizIntervencionesAdmin(Model model, @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFiltro){
		if(fechaFiltro == null) {
			model.addAttribute("mquirofanos",new ArrayList<>());
		}
		else {
			model.addAttribute("mquirofanos",quirofanoService.getMatriz(fechaFiltro));
		}
	    return "intervenciones/admin/matriz_intervenciones";
    }
	
	@GetMapping("/admin/reg_intervencion")
    public String regIntervencionForm(Model model){
        model.addAttribute("intervencion", new Intervencion());
        model.addAttribute("profesionales", profesionalService.getProfesionales());
        model.addAttribute("quirofanos", quirofanoService.getQuirofanos());
        return"intervenciones/admin/reg_intervencion";
    }
	
    @PostMapping("/admin/reg_intervencion")
    public String regIntervencion(@ModelAttribute Intervencion intervencion, Model model, RedirectAttributes redirectAttributes){
        try{
            model.addAttribute("intervencion", intervencionService.registrar(intervencion, intervencion.getQuirofano()));
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/intervenciones/admin/reg_intervencion";
            }
        return "redirect:/intervenciones/admin/vista_intervenciones";
    }

    @GetMapping("/admin/borrar/{id}")
    public String userDelete(@PathVariable("id") Long interventionId,
                             RedirectAttributes redirectAttributes){
        intervencionService.delete(interventionId);
        return "redirect:/intervenciones/admin/vista_intervenciones";
    }

    @GetMapping("/admin/editar/{id}")
    public String userEdit(@PathVariable("id") Long intervencionId, Model model){
        Intervencion intervencion  = intervencionService.findById(intervencionId);
        model.addAttribute("intervencion",intervencion);
        return "intervenciones/admin/editar";
    }

    @PostMapping("/admin/editar")
    public String update(@ModelAttribute Intervencion intervencion){
        intervencionService.update(intervencion);
        return "redirect:/intervenciones/admin/vista_intervenciones";
    }
    
    @GetMapping("/admin/vista_intervencion/{id}")
    public String vistaIntervencionAdmin(@PathVariable("id") Long intervencionId, Model model){
        Intervencion intervencion  = intervencionService.findById(intervencionId);
        model.addAttribute("intervencion",intervencion);
        return "intervenciones/admin/vista_intervencion";
    }
    
    @GetMapping("/user/vista_intervenciones")
    public String listaIntervencionesUser(Model model, @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFiltro){
		if(fechaFiltro == null) {
			model.addAttribute("intervenciones",intervencionService.getIntervencionesOrdenadas());
		}
		else {
			model.addAttribute("intervenciones",intervencionService.getIntervencionesFiltradas(fechaFiltro));
		}
	    return "intervenciones/user/vista_intervenciones";
    }
	
	@GetMapping("/user/matriz_intervenciones")
    public String matrizIntervencionesUser(Model model, @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFiltro){
		if(fechaFiltro == null) {
			model.addAttribute("mquirofanos",new ArrayList<>());
		}
		else {
			model.addAttribute("mquirofanos",quirofanoService.getMatriz(fechaFiltro));
		}
	    return "intervenciones/user/matriz_intervenciones";
    }
	
	@GetMapping("/user/vista_intervencion/{id}")
    public String vistaIntervencionUser(@PathVariable("id") Long intervencionId, Model model){
        Intervencion intervencion  = intervencionService.findById(intervencionId);
        model.addAttribute("intervencion",intervencion);
        return "intervenciones/user/vista_intervencion";
    }
}
