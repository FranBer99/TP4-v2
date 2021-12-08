package ar.edu.unnoba.poo2021.model.impl;

import ar.edu.unnoba.poo2021.model.dao.UsuarioDAO;
import ar.edu.unnoba.poo2021.model.entity.Usuario;
import ar.edu.unnoba.poo2021.model.service.UsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceimpl implements UsuarioService  {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public Usuario findByEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }

    @Override
    public Usuario registrar(Usuario u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return usuarioDAO.save(u);
    }

	@Override
	public Usuario getUsuario(Long id) {
		return usuarioDAO.findById(id).get();
	}

	@Override
	public List<Usuario> getUsuarios() {
		return usuarioDAO.findAll();
	}

	@Override
	public Usuario update(Usuario usuario) {
        Usuario uDB = usuarioDAO.findById(usuario.getId()).get();
        uDB.setNombre(usuario.getNombre());
        uDB.setApellido(usuario.getApellido());
        return usuarioDAO.save(uDB);
    }

	@Override
	public void delete(Long id) {
		usuarioDAO.deleteById(id);		
	}

}
