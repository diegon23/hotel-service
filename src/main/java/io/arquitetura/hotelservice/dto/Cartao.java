package io.arquitetura.hotelservice.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "card")
public class Cartao {

    @Element(name = "token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
