package io.arquitetura.hotelservice.service;

import io.arquitetura.hotelservice.dto.CreateReservaDTO;
import io.arquitetura.hotelservice.dto.type.StatusReservaType;
import io.arquitetura.hotelservice.entity.Cliente;
import io.arquitetura.hotelservice.entity.Quarto;
import io.arquitetura.hotelservice.entity.QuartoLimpeza;
import io.arquitetura.hotelservice.entity.Reserva;
import io.arquitetura.hotelservice.repository.ClienteRepository;
import io.arquitetura.hotelservice.repository.QuartoLimpezaRepository;
import io.arquitetura.hotelservice.repository.QuartoRepository;
import io.arquitetura.hotelservice.repository.ReservaRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    private final QuartoRepository quartoRepository;

    private final ClienteRepository clienteRepository;

    private final QuartoLimpezaRepository quartoLimpezaRepository;

    @Autowired
    private ReservaService(final ReservaRepository reservaRepository,
                           final QuartoRepository quartoRepository,
                           final ClienteRepository clienteRepository,
                           final QuartoLimpezaRepository quartoLimpezaRepository){
        this.reservaRepository = reservaRepository;
        this.quartoRepository = quartoRepository;
        this.clienteRepository = clienteRepository;
        this.quartoLimpezaRepository = quartoLimpezaRepository;
    }

    public <S extends Reserva> S save(S entity) {
        return reservaRepository.save(entity);
    }

    public Reserva finalizar(Long idReserva) throws Exception {
        Reserva reserva = reservaRepository.findById(idReserva).orElseThrow(() -> new NotFoundException("Não foi encontrado reserva com o id informado!"));
        reserva.setDataFim(Timestamp.valueOf(LocalDateTime.now()));

        Quarto quarto = quartoRepository.findById(reserva.getIdQuarto()).get();

        Long diasReserva = ChronoUnit.DAYS.between(reserva.getDataInicio().toLocalDateTime(), reserva.getDataFim().toLocalDateTime());

        reserva.setValorTotal(quarto.getValorDiaria().multiply(new BigDecimal(diasReserva)));

        reservaRepository.save(reserva);

        QuartoLimpeza quartoLimpeza = new QuartoLimpeza();
        quartoLimpeza.setIdQuarto(reserva.getIdQuarto());
        quartoLimpeza.setAtivo(true);

        quartoLimpezaRepository.save(quartoLimpeza);

        return reserva;
    }

    public Reserva criarNovaReserva(CreateReservaDTO createReserva) throws Exception {
        if(!clienteRepository.findById(createReserva.getIdCliente()).isPresent()){
            throw new Exception("O cliente informado não existe!");
        }

        Cliente cliente = clienteRepository.findById(createReserva.getIdCliente()).get();
        if(!cliente.isAtivo()){
            throw new Exception("Não é possível realizar uma reserva para um cliente inativo!");
        }

        Long diasReserva = ChronoUnit.DAYS.between(createReserva.getDtInicio(), createReserva.getDtFim());
        Optional<Quarto> quarto = quartoRepository.findById(createReserva.getIdQuarto());

        Timestamp dataInicio = Timestamp.valueOf(createReserva.getDtInicio().atStartOfDay());
        Timestamp dataFim = Timestamp.valueOf(createReserva.getDtFim().atStartOfDay());

        if(quarto.isPresent()){
            List<Reserva> reservas = new ArrayList<>();
            reservaRepository.findAll().forEach(reservas::add);

            if(!CollectionUtils.isEmpty(reservas)) {
                 Optional<Reserva> reservaOptional = reservas.stream()
                        .filter(r -> r.getIdQuarto().intValue() == quarto.get().getId().intValue())
                        .filter(r -> !r.isPagamentoRealizado())
                         .filter(r ->
                            ((r.getDataInicio().after(dataInicio) || r.getDataInicio().equals(dataInicio))
                                    && r.getDataInicio().before(dataFim)) ||
                            (r.getDataFim().after(dataInicio) && (r.getDataFim().before(dataFim) || r.getDataFim().equals(dataFim)))
                        )
                        .findAny();

                 if(reservaOptional.isPresent()){
                     throw new Exception("O quarto não está disponível nos dias selecionados!");
                 }
            }

            BigDecimal valorTotal = new BigDecimal(diasReserva).multiply(quarto.get().getValorDiaria());

            Reserva reserva = new Reserva();
            reserva.setDataInicio(dataInicio);
            reserva.setDataFim(dataFim);
            reserva.setIdCliente(createReserva.getIdCliente());
            reserva.setIdQuarto(createReserva.getIdQuarto());
            reserva.setPagamentoRealizado(false);
            reserva.setValorTotal(valorTotal);

            return reservaRepository.save(reserva);
        } else {
            return null;
        }
    }

    public Optional<Reserva> findById(Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);

        if(reserva.isPresent()){
            reserva.get().setStatus(getStatusReserva(reserva.get()));
        }

        return reserva;
    }

    public Reserva findByTransacao(String transacao) {
        Reserva reserva = reservaRepository.findByTransacao(transacao);

        return reserva;
    }

    public Iterable<Reserva> findAll() {
        Iterable<Reserva> reservas = reservaRepository.findAll();

        reservas.forEach(reserva -> {
            reserva.setStatus(getStatusReserva(reserva));
        });

        return reservas;
    }

    public Optional<Reserva> findByQuarto(Long idQuarto){
        List<Reserva> reservas = new ArrayList<>();
        reservaRepository.findAll().forEach(reservas::add);

        if(!CollectionUtils.isEmpty(reservas)) {
            return reservas.stream()
                    .filter(r -> r.getIdQuarto().intValue() == idQuarto.intValue())
                    .filter(r -> r.getDataInicio().equals(new Timestamp(System.currentTimeMillis()))
                            || r.getDataInicio().before(new Timestamp(System.currentTimeMillis())))
                    .filter(r -> r.getDataFim().after(new Timestamp(System.currentTimeMillis())))
                    .filter(r -> !r.isPagamentoRealizado())
                    .findAny();
        } else {
            return Optional.empty();
        }

    }

    public void deleteById(Long aLong) {
        reservaRepository.deleteById(aLong);
    }

    public void delete(Reserva entity) {
        reservaRepository.delete(entity);
    }

    private String getStatusReserva(Reserva reserva){
        if(reserva.getDataFim().after(Timestamp.from(Instant.now()))){
            if(reserva.isPagamentoRealizado()){
                return StatusReservaType.PAGAMENTO_REALIZADO.name();
            } else {
                return StatusReservaType.PAGAMENTO_PENDENTE.name();
            }
        } else {
            if(reserva.isPagamentoRealizado()){
                return StatusReservaType.FINALIZADA.name();
            } else {
                return StatusReservaType.PAGAMENTO_ATRASADO.name();
            }
        }
    }
}
