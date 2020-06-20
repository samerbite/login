package cl.titicoctel.login.repository;

import cl.titicoctel.login.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    Usuarios findByCorreo (String correo);
}
