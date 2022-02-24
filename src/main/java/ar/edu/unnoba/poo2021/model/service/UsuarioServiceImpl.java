package ar.edu.unnoba.poo2021.model.service;

import ar.edu.unnoba.poo2021.model.entity.Rol;
import ar.edu.unnoba.poo2021.model.entity.Usuario;
import ar.edu.unnoba.poo2021.model.repository.RolRepository;
import ar.edu.unnoba.poo2021.model.repository.UsuarioRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    
    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario registrar(Usuario u) throws Exception{
        if(usuarioRepository.findByEmail(u.getEmail())== null){
            u.setPassword(passwordEncoder.encode(u.getPassword()));
            Rol userRol = rolRepository.getById((long) 2);
            Set<Rol> roles = new HashSet<>();
            roles.add(userRol);
            u.setRoles(roles);
            return usuarioRepository.save(u);
        }else{
            throw new Exception("Este mail no esta disponible, pruebe otro");
        }
    }

	@Override
	public Usuario getUsuario(Long id) {
		return usuarioRepository.findById(id).get();
	}

	@Override
	public List<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
	}

    @Override
	public Usuario update(Usuario usuario) {
        Usuario uDB = usuarioRepository.findById(usuario.getId()).get();
        uDB.setNombre(usuario.getNombre());
        uDB.setApellido(usuario.getApellido());
        return usuarioRepository.save(uDB);
    }

    @Override
    public void delete(User sessionUser,Long id) throws Exception{
        Usuario usuario = usuarioRepository.findByEmail(sessionUser.getUsername());
        if(usuario.getId().equals(id)){
            throw new Exception("Un usuario no se puede borrar a si mismo");
        }
        usuarioRepository.deleteById(id);
    }

	@Override
	public List<Usuario> getUsuariosOrdenados() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		usuarios.sort((o1, o2) -> (o1.getApellido() + o1.getNombre()).compareTo(o1.getApellido() + o1.getNombre()));
    	return usuarios;
	}

}
