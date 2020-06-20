package cl.titicoctel.login.service;

import cl.titicoctel.login.model.Blacklist;
import org.springframework.http.ResponseEntity;

public interface BlacklistService {
    ResponseEntity<Blacklist>guardarJWT(Blacklist blacklist);
}
