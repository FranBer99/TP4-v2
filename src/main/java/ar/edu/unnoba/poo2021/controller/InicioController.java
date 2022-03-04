package ar.edu.unnoba.poo2021.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String inicio(){
        return "inicio";
    }
    
    @GetMapping("/logout")
    public String logout(){
        return "inicio";
    }

    @GetMapping("/error")
    public String handleError() {
        return "/error";
    }
}