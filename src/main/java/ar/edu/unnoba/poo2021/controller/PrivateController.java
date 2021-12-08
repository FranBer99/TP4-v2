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

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/private")
public class PrivateController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/index")
    public String index(Model model, @ModelAttribute("error") String errorMessage){
        if(errorMessage!=null){
            model.addAttribute("error",errorMessage);
        }
        model.addAttribute("usuarios",usuarioService.getUsuarios());
        return "index";
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

        return "redirect:/private/index";
    }


    @GetMapping("/editar/{id}")
    public String userEdit(@PathVariable("id") Long userId, Model model){
        Usuario usuario = usuarioService.getUsuario(userId);
        model.addAttribute("usuario",usuario);
        return "editar";
    }

    @PostMapping("/editar")
    public String update(@ModelAttribute Usuario usuario){
        usuarioService.update(usuario);
        return "redirect:/private/index";
    }
}
