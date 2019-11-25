package io.arquitetura.hotelservice.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "transaction", strict = false)
public class Transaction {

    @Element(name = "code")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
