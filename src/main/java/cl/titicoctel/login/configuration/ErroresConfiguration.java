package cl.titicoctel.login.configuration;

import cl.titicoctel.login.dto.ErrorDto;
import cl.titicoctel.login.exception.ApiExceptions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "errores")
public class ErroresConfiguration {
    private ErrorDto camposVacios;
    private ErrorDto camposNulos;
    private ErrorDto registroNoExiste;
    private ErrorDto errorGlobal;

    public ApiExceptions obtenerErrorDeNegocio(ErrorDto errorDto){
        return new ApiExceptions(errorDto.getCodigo(), errorDto.getMensaje());
    }
}
