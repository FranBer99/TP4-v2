package ar.edu.unnoba.poo2021.config;

import ar.edu.unnoba.poo2021.model.entity.Usuario;
import ar.edu.unnoba.poo2021.model.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceimpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = usuarioDAO.findByEmail(s);
        User.UserBuilder builder = null;
        if(usuario != null){
            builder= User.withUsername(s);
            builder.disabled(false);
            builder.password(usuario.getPassword());
            builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
        }else{
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return builder.build();
    }
}
