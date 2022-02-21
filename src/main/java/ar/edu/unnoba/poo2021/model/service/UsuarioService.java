package ar.edu.unnoba.poo2021.model.service;

import java.util.List;
import ar.edu.unnoba.poo2021.model.entity.Usuario;
import org.springframework.security.core.userdetails.User;

public interface UsuarioService {
    public Usuario findByEmail(String email);
    public Usuario registrar(Usuario user) throws Exception;
    public Usuario getUsuario(Long id);
    public List<Usuario> getUsuarios();
    public Usuario update(Usuario user);
    public void delete(User sessionUser, Long id)throws Exception;
    public List<Usuario> getUsuariosOrdenados();
}
