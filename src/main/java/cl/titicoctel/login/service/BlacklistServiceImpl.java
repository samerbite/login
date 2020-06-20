package cl.titicoctel.login.service;

import cl.titicoctel.login.model.Blacklist;
import cl.titicoctel.login.repository.BlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BlacklistServiceImpl implements BlacklistService{
    @Autowired
    private BlacklistRepository blacklistRepository;

    @Override
    public ResponseEntity<Blacklist>guardarJWT(Blacklist blacklist){
        Blacklist response = blacklistRepository.save(blacklist);
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }
}
