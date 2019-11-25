package io.arquitetura.hotelservice.service;

import io.arquitetura.hotelservice.client.CartaoClient;
import io.arquitetura.hotelservice.client.PagSeguroClient;
import io.arquitetura.hotelservice.dto.Cartao;
import io.arquitetura.hotelservice.dto.Session;
import io.arquitetura.hotelservice.dto.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CartaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartaoService.class);

    private CartaoClient cartaoClient;

    @Autowired
    public CartaoService(final CartaoClient cartaoClient) {
        this.cartaoClient = cartaoClient;
    }



    public Cartao buscarTokenCartao(String sessionId) throws IOException {
        return this.cartaoClient.buscarToken(sessionId,
                "10000",
                "4111111111111111",
                "visa",
                "123",
                "12",
                "2030").execute().body();
    }
}