package ar.edu.unnoba.poo2021.model.service;

import ar.edu.unnoba.poo2021.model.entity.Intervencion;
import ar.edu.unnoba.poo2021.model.repository.IntervencionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IntervencionServiceImpl implements IntervencionService{

    @Autowired
    IntervencionRepository intervencionRepository;

    @Override
    public Intervencion findById(Long id) {
        return intervencionRepository.findByID(id);
    }

    @Override
    public Intervencion registrar(Intervencion intervencion) throws Exception{
        if( intervencionRepository.findByFechaHora(intervencion.getFechaHoraInicio(), intervencion.getFechaHoraFin()) != null){
            return null;
        }else{
            throw new Exception("Esta fecha no esta disponible, pruebe otra");
        }
    }

    @Override
    public List<Intervencion> getIntervenciones() {
        return null;
    }

    @Override
    public Intervencion update(Intervencion intervencion) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }


    /*

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
    * */
}