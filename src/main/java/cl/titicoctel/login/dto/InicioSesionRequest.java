package cl.titicoctel.login.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InicioSesionRequest {
    private String usernamer;
    private String password;
}
