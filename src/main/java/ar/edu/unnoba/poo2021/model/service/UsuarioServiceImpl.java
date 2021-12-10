package ar.edu.unnoba.poo2021.model.service;

import ar.edu.unnoba.poo2021.model.entity.Usuario;
import ar.edu.unnoba.poo2021.model.repository.UsuarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioDAO;

    @Override
    public Usuario findByEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }

    @Override
    public Usuario registrar(Usuario u) throws Exception{
        if(usuarioDAO.findByEmail(u.getEmail())== null){
            u.setPassword(passwordEncoder.encode(u.getPassword()));
            return usuarioDAO.save(u);
        }else{
            throw new Exception("Este mail no esta disponible, pruebe otro");
        }

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
    public void delete(User sessionUser,Long id) throws Exception{
        Usuario usuario = usuarioDAO.findByEmail(sessionUser.getUsername());
        if(usuario.getId().equals(id)){
            throw new Exception("Un usuario no se puede borrar a si mismo");
        }
        usuarioDAO.deleteById(id);
    }

}
