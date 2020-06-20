package cl.titicoctel.login.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InicioSesionResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private String jwttoken;
}
