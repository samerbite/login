package cl.titicoctel.login.dto;

import java.io.Serializable;

public class JwtDtoResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;

    private String jwttoken;

    public JwtDtoResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }	public String getToken() {
        return this.jwttoken;
    }
}

