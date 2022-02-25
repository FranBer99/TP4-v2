package ar.edu.unnoba.poo2021.controller;

import ar.edu.unnoba.poo2021.model.entity.Usuario;
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

    @GetMapping("/admin/inicio")
    public String inicioAdmin(){
        return "usuarios/admin/inicio";
    }

    @GetMapping("/admin/lista")
    public String index(Model model, @ModelAttribute("error") String errorMessage){
        if(errorMessage!=null){
            model.addAttribute("error",errorMessage);
        }
        model.addAttribute("usuarios",usuarioService.getUsuariosOrdenados());
        return "usuarios/admin/lista";
    }

    @GetMapping("/admin/borrar/{id}")
    public String userDelete(@PathVariable("id") Long userId,
                             Authentication authentication,
                             RedirectAttributes redirectAttributes){
        User userSession = (User) authentication.getPrincipal();
        try{
            usuarioService.delete(userSession,userId);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
        }

        return "redirect:/usuarios/admin/lista";
    }


    @GetMapping("/admin/editar/{id}")
    public String userEdit(@PathVariable("id") Long userId, Model model){
        Usuario usuario = usuarioService.getUsuario(userId);
        model.addAttribute("usuario",usuario);
        return "usuarios/admin/editar";
    }

    @PostMapping("/admin/editar")
    public String update(@ModelAttribute Usuario usuario){
        usuarioService.update(usuario);
        return "redirect:/usuarios/admin/lista";
    }
    
    @GetMapping("/user/inicio")
    public String inicioUsuarios(){
        return "usuarios/user/inicio";
    }
    
    @GetMapping("/logout")
    public String logout(){
        return"/logout";
    }
}
