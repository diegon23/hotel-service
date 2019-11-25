package io.arquitetura.hotelservice.service;

import io.arquitetura.hotelservice.client.PagSeguroClient;
import io.arquitetura.hotelservice.dto.Cartao;
import io.arquitetura.hotelservice.dto.Session;
import io.arquitetura.hotelservice.dto.Transaction;
import io.arquitetura.hotelservice.entity.Reserva;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class PagSeguroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PagSeguroService.class);

    private PagSeguroClient pagSeguroClient;

    private CartaoService cartaoService;

    private ReservaService reservaService;

    @Autowired
    public PagSeguroService(final PagSeguroClient pagSeguroClient,
                            CartaoService cartaoService,
                            ReservaService reservaService) {
        this.pagSeguroClient = pagSeguroClient;
        this.cartaoService = cartaoService;
        this.reservaService = reservaService;
    }

    public Session criarSessao() throws IOException {
        return this.pagSeguroClient.criarSessao("nogueiraadiegoo@gmail.com","BD7558B466FB42A29DAA3C324CD4F0C5").execute().body();
    }

    public void estornarTransacao(String transactionCode) throws IOException {
        this.pagSeguroClient.estornarCompra("nogueiraadiegoo@gmail.com","BD7558B466FB42A29DAA3C324CD4F0C5", transactionCode).execute().body();

        Reserva reserva = reservaService.findByTransacao(transactionCode);

        reserva.setPagamentoEstornado(true);
        reservaService.save(reserva);
    }

    public Transaction criarTransacao(Long idReserva) throws Exception {
        Session session = this.criarSessao();

        Cartao cartao = cartaoService.buscarTokenCartao(session.getId());

        final var reserva = reservaService.finalizar(idReserva);

        final var valorReserva = reserva.getValorTotal().intValue() == 0 ? 1L : reserva.getValorTotal();

        Transaction transaction =  this.pagSeguroClient.criarTransacao(
                "nogueiraadiegoo@gmail.com",
                "BD7558B466FB42A29DAA3C324CD4F0C5",
                "default",
                "creditCard",
                "nogueiraadiegoo@gmail.com",
                "BRL",
                "0001",
                "NotebookPrata",
                String.valueOf(valorReserva)+".00",
                "1",
                "https://sualoja.com.br/notifica.html",
                "REF1234",
                "Jose Antonio",
                "22111944785",
                "11",
                "56273440",
                "c75023832799195738317@sandbox.pagseguro.com.br",
                "172.22.204.53",
                "Av.Brig.FariaLima",
                "1384",
                "5oandar",
                "JardimPaulistano",
                "01452002",
                "SaoPaulo",
                "SP",
                "BRA",
                "1",
                "0.00",
                cartao.getToken(),
                "1",
                String.valueOf(valorReserva)+".00",
                "2",
                "Jose Antonio",
                "22111944785",
                "27/10/1987",
                "11",
                "56273440",
                "Av.Brig.FariaLima",
                "1384",
                "5oandar",
                "JardimPaulistano",
                "01452002",
                "SaoPaulo",
                "SP",
                "BRA").execute().body();

        reserva.setTransacao(transaction.getCode().replaceAll("-", ""));
        reserva.setPagamentoRealizado(true);

        reservaService.save(reserva);

        return transaction;
    }
}