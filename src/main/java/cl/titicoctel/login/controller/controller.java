package cl.titicoctel.login.controller;

import cl.titicoctel.login.dto.InicioSesionRequest;
import cl.titicoctel.login.dto.InicioSesionResponse;
import cl.titicoctel.login.model.Blacklist;
import cl.titicoctel.login.service.BlacklistService;
import cl.titicoctel.login.service.InicioSesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/titicoctel")
public class controller {
    @Autowired
    private BlacklistService blacklistService;
    @Autowired
    private InicioSesionService inicioSesionService;

    @PostMapping(value="/inicioSesion")
    public ResponseEntity<?>inicioSesion(@RequestBody InicioSesionRequest inicioSesionRequest){
        ResponseEntity<InicioSesionResponse> response = inicioSesionService.inicioSesion(inicioSesionRequest);
        return ResponseEntity.ok(response);
    }
}
