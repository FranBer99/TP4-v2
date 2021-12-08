package ar.edu.unnoba.poo2021.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ar.edu.unnoba.poo2021.model.entity.Usuario;
import ar.edu.unnoba.poo2021.model.service.UsuarioService;

@RequestMapping("/usuarios")
public class UsuarioController {
	    @Autowired
	    private UsuarioService userService;

	    @GetMapping
	    public String getUsers(Model model){
	        model.addAttribute("usuarios",userService.getUsuarios());
	        return "usuarios/lista";
	    }
	    
	    @GetMapping("/register")
	    public String userNew(Model model){
	        model.addAttribute("usuario",new Usuario());
	        return "usuarios/register";
	    }

	    @PostMapping("/register")
	    public String create(@ModelAttribute Usuario user){
	        userService.registrar(user);
	        return "redirect:/usuarios";
	    }
	    
	    @GetMapping("/borrar/{id}")
	    public String userDelete(@PathVariable("id") Long userId){
	        userService.delete(userId);
	        return "redirect:/usuarios";
	    }
	    
	    @GetMapping("/editar/{id}")
	    public String userEdit(@PathVariable("id") Long userId, Model model){
	        Usuario usuario = userService.getUsuario(userId);
	        model.addAttribute("usuario",usuario);
	        return "usuarios/editar";
	    }
	    
	    @PostMapping("/editar")
	    public String update(@ModelAttribute Usuario usuario){
	        userService.update(usuario);
	        return "redirect:/usuarios";
	    }
}
