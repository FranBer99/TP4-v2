package ar.edu.unnoba.poo2021.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ar.edu.unnoba.poo2021.model.entity.Usuario;
import ar.edu.unnoba.poo2021.model.service.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/auth/login")
    public String login(Model model){
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @GetMapping("/auth/registro")
    public String registroForm(Model model){
        model.addAttribute("usuario",new Usuario());
        return "registro";
    }

    @PostMapping("/auth/registro")
    public String registro(@ModelAttribute Usuario usuario, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "redirect:/auth/registro";
        }else{
            try{
                model.addAttribute("usuario", usuarioService.registrar(usuario));
            }catch(Exception e){
                redirectAttributes.addFlashAttribute("error",e.getMessage());
                return "redirect:/auth/registro";
            }
        }
        return "redirect:/auth/login";
    }
    
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/usuarios/admin/inicio";
        }
        return "redirect:/usuarios/user/inicio";
    }
}
