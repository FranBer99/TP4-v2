package ar.edu.unnoba.poo2021.model.service;

import java.util.List;
import ar.edu.unnoba.poo2021.model.entity.Usuario;

public interface UsuarioService {
    public Usuario findByEmail(String email);
    public Usuario registrar(Usuario user);
    public Usuario getUsuario(Long id);
    public List<Usuario> getUsuarios();
    public Usuario update(Usuario user);
    public void delete(Long id);
}
