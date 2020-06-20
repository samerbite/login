package cl.titicoctel.login.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name="usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String correo;
    private String password;
    @CreationTimestamp
    private Timestamp fechaCreacion;
    private Timestamp ultimoLogin;

    public Usuarios(){}
}
