package cl.titicoctel.login.exception;

import cl.titicoctel.login.dto.ErrorDto;

public class ApiExceptions extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final String codigo;

    public ApiExceptions(String codigo, String mensaje){
        super(mensaje);
        this.codigo = codigo;
    }

    public ApiExceptions(ErrorDto error) {
        super(error.getMensaje());
        this.codigo = error.getCodigo();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return getMessage();
    }

}
