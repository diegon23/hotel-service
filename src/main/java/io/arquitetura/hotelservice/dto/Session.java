package io.arquitetura.hotelservice.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "session")
public class Session {

    @Element(name = "id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
