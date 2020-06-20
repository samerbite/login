package cl.titicoctel.login.service;

import cl.titicoctel.login.model.Usuarios;
import cl.titicoctel.login.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtServiceImpl implements UserDetailsService{
    @Autowired
    private UsuariosRepository usuariosRepository;
    @Override
    public UserDetails loadUserByUsername(String username){
        Usuarios usuarios = usuariosRepository.findByCorreo(username);
        return new User(usuarios.getCorreo(), usuarios.getPassword(), new ArrayList<>());
    }
}
