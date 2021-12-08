package ar.edu.unnoba.poo2021.controller;

import ar.edu.unnoba.poo2021.model.entity.Usuario;
import ar.edu.unnoba.poo2021.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/private")
public class PrivateController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/index")
    public String index(Authentication authentication, HttpSession session){
        String username = authentication.getName();
        if(session.getAttribute("usuario") == null){
            Usuario usuario = usuarioService.findByEmail(username);
            usuario.setPassword(null);
            session.setAttribute("usuario", usuario);
        }
        return "index";
    }
}
