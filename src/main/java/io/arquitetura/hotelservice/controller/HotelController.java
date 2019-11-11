package io.arquitetura.hotelservice.controller;

import io.arquitetura.hotelservice.entity.Cliente;
import io.arquitetura.hotelservice.entity.Quarto;
import io.arquitetura.hotelservice.entity.Reserva;
import io.arquitetura.hotelservice.service.ClienteService;
import io.arquitetura.hotelservice.service.QuartoService;
import io.arquitetura.hotelservice.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final ClienteService clienteService;

    private final ReservaService reservaService;

    private final QuartoService quartoService;

    @Autowired
    public HotelController(final ClienteService clienteService,
                           final ReservaService reservaService,
                           final QuartoService quartoService) {
        this.clienteService = clienteService;
        this.reservaService = reservaService;
        this.quartoService = quartoService;
    }

    @GetMapping(value = "/consultar-clientes")
    public Iterable<Cliente> consultarClientes() throws Exception {
        return clienteService.findAll();
    }

    @PostMapping(value = "/salvar-cliente")
    public Cliente salvarCliente(@RequestBody Cliente cliente) throws Exception {
        return clienteService.save(cliente);
    }

    @GetMapping(value = "/consultar-reservas")
    public Iterable<Reserva> consultarReservas() throws Exception {
        return reservaService.findAll();
    }

    @PostMapping(value = "/salvar-reserva")
    public Reserva salvarReserva(@RequestBody Reserva reserva) throws Exception {
        return reservaService.save(reserva);
    }

    @GetMapping(value = "/consultar-quartos")
    public Iterable<Quarto> consultarQuartos() throws Exception {
        return quartoService.findAll();
    }

    @PostMapping(value = "/salvar-quarto")
    public Quarto salvarQuarto(@RequestBody Quarto quarto) throws Exception {
        return quartoService.save(quarto);
    }

}
