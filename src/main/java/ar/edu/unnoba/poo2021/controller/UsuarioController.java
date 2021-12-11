package ar.edu.unnoba.poo2021.controller;

import ar.edu.unnoba.poo2021.model.entity.Intervencion;
import ar.edu.unnoba.poo2021.model.entity.Usuario;
import ar.edu.unnoba.poo2021.model.service.IntervencionService;
import ar.edu.unnoba.poo2021.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IntervencionService intervencionService;

    @GetMapping("/inicio_usuarios")
    public String inicioUsuarios(){
        return "usuarios/inicio_usuarios";
    }

    @GetMapping("/vista_intervenciones")
    public String listaIntervenciones(){
        return "usuarios/vista_intervenciones";
    }

    @GetMapping("/lista")
    public String index(Model model, @ModelAttribute("error") String errorMessage){
        if(errorMessage!=null){
            model.addAttribute("error",errorMessage);
        }
        model.addAttribute("usuarios",usuarioService.getUsuarios());
        return "usuarios/lista";
    }

    @GetMapping("/borrar/{id}")
    public String userDelete(@PathVariable("id") Long userId,
                             Authentication authentication,
                             RedirectAttributes redirectAttributes){
        User userSession = (User) authentication.getPrincipal();
        try{
            usuarioService.delete(userSession,userId);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
        }

        return "redirect:/usuarios/lista";
    }


    @GetMapping("/editar/{id}")
    public String userEdit(@PathVariable("id") Long userId, Model model){
        Usuario usuario = usuarioService.getUsuario(userId);
        model.addAttribute("usuario",usuario);
        return "usuarios/editar";
    }

    @PostMapping("/editar")
    public String update(@ModelAttribute Usuario usuario){
        usuarioService.update(usuario);
        return "redirect:/usuarios/lista";
    }
    
    @GetMapping("/logout")
    public String logout(){
        return"usuarios/logout";
    }

    @GetMapping("/reg_intervencion")
    public String regIntervencionForm(Model model){
        model.addAttribute("intervencion", new Intervencion());
        return"usuarios/reg_intervencion";
    }
    @PostMapping("/reg_intervencion")
    public String regIntervencion(@ModelAttribute Intervencion intervencion, Model model, RedirectAttributes redirectAttributes){
        try{
            model.addAttribute("intervencion", intervencionService.registrar(intervencion));
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/usuarios/reg_intervencion"; }
        return "redirect:/usuarios/reg_intervencion";
    }
}
