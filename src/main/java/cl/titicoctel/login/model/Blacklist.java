package cl.titicoctel.login.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@Table(name="blacklist")
public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idUsuario;
    private String correo;
    private String jwt;
    @CreationTimestamp
    private Timestamp fechaJWT;

    public Blacklist(){}
}
