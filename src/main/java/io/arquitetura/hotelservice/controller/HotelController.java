package io.arquitetura.hotelservice.controller;

import io.arquitetura.hotelservice.dto.CreateReservaDTO;
import io.arquitetura.hotelservice.dto.Transaction;
import io.arquitetura.hotelservice.entity.Cliente;
import io.arquitetura.hotelservice.entity.Quarto;
import io.arquitetura.hotelservice.entity.QuartoLimpeza;
import io.arquitetura.hotelservice.entity.Reserva;
import io.arquitetura.hotelservice.service.ClienteService;
import io.arquitetura.hotelservice.service.PagSeguroService;
import io.arquitetura.hotelservice.service.QuartoLimpezaService;
import io.arquitetura.hotelservice.service.QuartoService;
import io.arquitetura.hotelservice.service.ReservaService;
import io.arquitetura.hotelservice.dto.Session;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final ClienteService clienteService;

    private final ReservaService reservaService;

    private final QuartoService quartoService;

    private final QuartoLimpezaService quartoLimpezaService;

    private final PagSeguroService pagSeguroService;

    @Autowired
    public HotelController(final ClienteService clienteService,
                           final ReservaService reservaService,
                           final QuartoService quartoService,
                           final QuartoLimpezaService quartoLimpezaService,
                           final PagSeguroService pagSeguroService) {
        this.clienteService = clienteService;
        this.reservaService = reservaService;
        this.quartoService = quartoService;
        this.quartoLimpezaService = quartoLimpezaService;
        this.pagSeguroService = pagSeguroService;
    }

    @GetMapping(value = "/consultar-clientes")
    public Iterable<Cliente> consultarClientes() throws Exception {
        return clienteService.findAll();
    }

    @GetMapping(value = "/consultar-clientes/{cpf}")
    public Cliente consultarClientesByCpf(@PathVariable("cpf") String cpf) throws Exception {
        Cliente cliente =  clienteService.findById(cpf).orElseThrow(() -> new NotFoundException("Não foi encontrado cliente com o cpf informado!"));

        if(cliente.isAtivo()){
            return cliente;
        } else {
            throw new NotFoundException("Não foi encontrado cliente ativo com o cpf informado!");
        }
    }

    @PostMapping(value = "/salvar-cliente")
    public Cliente salvarCliente(@RequestBody Cliente cliente) throws Exception {
        return clienteService.save(cliente);
    }

    @PostMapping(value = "/inativar-cliente/{cpf}")
    public Cliente inativarCliente(@PathVariable("cpf") String cpf) throws Exception {
        Cliente cliente = clienteService.findById(cpf).get();
        cliente.setAtivo(false);
        return clienteService.save(cliente);
    }

    @PostMapping(value = "/ativar-cliente/{cpf}")
    public Cliente ativarCliente(@PathVariable("cpf") String cpf) throws Exception {
        Cliente cliente = clienteService.findById(cpf).get();
        cliente.setAtivo(true);
        return clienteService.save(cliente);
    }

    @GetMapping(value = "/consultar-reservas")
    public Iterable<Reserva> consultarReservas() throws Exception {
        return reservaService.findAll();
    }

    @PostMapping(value = "/salvar-reserva")
    public Reserva salvarReserva(@RequestBody CreateReservaDTO reserva) throws Exception {
        return reservaService.criarNovaReserva(reserva);
    }

    @GetMapping(value = "/consultar-quartos")
    public Iterable<Quarto> consultarQuartos() throws Exception {
        return quartoService.findAll();
    }

    @GetMapping(value = "/consultar-quartos/{id}")
    public Quarto consultarQuartoById(@PathVariable("id") Long id) throws Exception {
        return quartoService.findById(id).orElseThrow(() -> new NotFoundException("Não foi encontrado quarto com o id informado!"));
    }

    @GetMapping(value = "/consultar-quartos-disponiveis")
    public Set<Quarto> consultarQuartosDisponiveis() throws Exception {
        return quartoService.consultarQuartosDisponiveis().stream()
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(Quarto::getModelo))));
    }

    @PostMapping(value = "/salvar-quarto")
    public Quarto salvarQuarto(@RequestBody Quarto quarto) throws Exception {
        return quartoService.save(quarto);
    }

    @GetMapping(value = "/finalizar-reserva/{idReserva}")
    public Reserva finalizarReserva(@PathVariable Long idReserva) throws Exception {
        return reservaService.finalizar(idReserva);
    }

    @GetMapping(value = "/consultar-quartos-limpeza")
    public Iterable<QuartoLimpeza> consultarQuartosLimpeza() throws Exception {
        return quartoLimpezaService.findAll();
    }

    @GetMapping(value = "/finalizar-limpeza/{idLimpeza}")
    public QuartoLimpeza finalizarLimpeza(@PathVariable Long idLimpeza) throws Exception {
        QuartoLimpeza quartoLimpeza = quartoLimpezaService.findById(idLimpeza).get();
        quartoLimpeza.setAtivo(false);
        return quartoLimpezaService.save(quartoLimpeza);
    }

    @PostMapping(value = "/criar-transacao/{idReserva}")
    public Transaction criarTransacao(@PathVariable("idReserva") Long idReserva) throws Exception {
        return pagSeguroService.criarTransacao(idReserva);
    }

    @PostMapping(value = "/estornar-transacao/{transactionId}")
    public void session(@PathVariable("transactionId") String transactionId) throws Exception {
        pagSeguroService.estornarTransacao(transactionId);
    }

}
