package cl.titicoctel.login.service;

import cl.titicoctel.login.dto.InicioSesionRequest;
import cl.titicoctel.login.dto.InicioSesionResponse;
import org.springframework.http.ResponseEntity;

public interface InicioSesionService {
    ResponseEntity<InicioSesionResponse>inicioSesion(InicioSesionRequest inicioSesionRequest);
}
