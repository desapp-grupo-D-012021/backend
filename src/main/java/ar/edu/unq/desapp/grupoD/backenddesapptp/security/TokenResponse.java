package ar.edu.unq.desapp.grupoD.backenddesapptp.security;

public class TokenResponse {

    private final String token;

    public TokenResponse(String token) {
        this.token = token;
    }
    public String getToken(){
        return this.token;
    }
}
