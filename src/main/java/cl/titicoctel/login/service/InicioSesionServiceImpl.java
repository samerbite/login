package cl.titicoctel.login.service;

import cl.titicoctel.login.configuration.ErroresConfiguration;
import cl.titicoctel.login.configuration.JwtTokenUtil;
import cl.titicoctel.login.dto.InicioSesionRequest;
import cl.titicoctel.login.dto.InicioSesionResponse;
import cl.titicoctel.login.model.Blacklist;
import cl.titicoctel.login.model.Usuarios;
import cl.titicoctel.login.repository.UsuariosRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

@Service
public class InicioSesionServiceImpl implements InicioSesionService{
    @Autowired
    private BlacklistService blacklistService;
    @Autowired
    private JwtServiceImpl jwtService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UsuariosRepository usuariosRepository;
    @Autowired
    private ErroresConfiguration erroresConfiguration;

    @Override
    public ResponseEntity<InicioSesionResponse>inicioSesion(InicioSesionRequest inicioSesionRequest){
        if (inicioSesionRequest.getPassword().equals("") || inicioSesionRequest.getUsernamer().equals("")){
            return new ResponseEntity(erroresConfiguration.getCamposVacios(), HttpStatus.BAD_REQUEST);
        }
        if (inicioSesionRequest.getUsernamer()==null || inicioSesionRequest.getPassword()==null){
            return new ResponseEntity(erroresConfiguration.getCamposNulos(), HttpStatus.BAD_REQUEST);
        }
        boolean userExiste = existeUsuario(inicioSesionRequest.getUsernamer());
        boolean passwordValida = validaPassword(inicioSesionRequest.getUsernamer(), inicioSesionRequest.getPassword());
        if (userExiste && passwordValida) {
            UserDetails userDetails = jwtService.loadUserByUsername(inicioSesionRequest.getUsernamer());
            String token = jwtTokenUtil.generateToken(userDetails);
            Usuarios usuarios = usuariosRepository.findByCorreo(inicioSesionRequest.getUsernamer());
            Blacklist blacklist = new Blacklist();
            blacklist.setJwt(token);
            blacklist.setCorreo(usuarios.getCorreo());
            blacklist.setIdUsuario(usuarios.getId());
            blacklistService.guardarJWT(blacklist);
            actualizarUsuarioLastLogin (usuarios.getId(), usuarios);
            InicioSesionResponse inicioSesionResponse = InicioSesionResponse.builder()
                    .jwttoken(token)
                    .build();
            return new ResponseEntity(inicioSesionResponse, HttpStatus.ACCEPTED);
        }
        if (!userExiste){
            return new ResponseEntity(erroresConfiguration.getRegistroNoExiste(), HttpStatus.NOT_FOUND);
        }
        if (!passwordValida){
            return new ResponseEntity(erroresConfiguration.getRegistroNoExiste(), HttpStatus.PRECONDITION_REQUIRED);
        }
            return new ResponseEntity(erroresConfiguration.getErrorGlobal(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private boolean existeUsuario (String username){
        Usuarios usuarios = usuariosRepository.findByCorreo(username);
        boolean existe = false;
        if(usuarios.getCorreo().equals(username)){
            existe = true;
        }
        return existe;
    }

    private boolean validaPassword (String username, String password){
        Usuarios usuarios = usuariosRepository.findByCorreo(username);
        boolean pswdCorrecta = false;
        String pswdAlmacenada = usuarios.getPassword();
        String pswdEncryptada = Encryptarpassword(password);
        if(pswdAlmacenada.equals(pswdEncryptada)){
            pswdCorrecta = true;
        }
        return pswdCorrecta;
    }

    private String Encryptarpassword (String password){
        String secretKey = password;
        String base64EncryptedString = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTextBytes = password.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    private void actualizarUsuarioLastLogin (Long Id, Usuarios usuarios){
        Date date = new Date();
        Usuarios user = new Usuarios();
        user.setId(Id);
        user.setCorreo(usuarios.getCorreo());
        user.setPassword(usuarios.getPassword());
        user.setFechaCreacion(usuarios.getFechaCreacion());
        user.setUltimoLogin(new Timestamp(date.getTime()));
        usuariosRepository.save(user);
    }

}
