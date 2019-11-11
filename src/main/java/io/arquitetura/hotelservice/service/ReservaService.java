package io.arquitetura.hotelservice.service;

import io.arquitetura.hotelservice.dto.type.StatusReservaType;
import io.arquitetura.hotelservice.entity.Reserva;
import io.arquitetura.hotelservice.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    private ReservaService(final ReservaRepository reservaRepository){
        this.reservaRepository = reservaRepository;
    }

    public <S extends Reserva> S save(S entity) {
        return reservaRepository.save(entity);
    }

    public Optional<Reserva> findById(Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);

        if(reserva.isPresent()){
            reserva.get().setStatus(getStatusReserva(reserva.get()));
        }

        return reserva;
    }

    public Iterable<Reserva> findAll() {
        Iterable<Reserva> reservas = reservaRepository.findAll();

        reservas.forEach(reserva -> {
            reserva.setStatus(getStatusReserva(reserva));
        });

        return reservas;
    }

    public void deleteById(Long aLong) {
        reservaRepository.deleteById(aLong);
    }

    public void delete(Reserva entity) {
        reservaRepository.delete(entity);
    }

    private String getStatusReserva(Reserva reserva){
        if(reserva.getDataFim().before(Timestamp.from(Instant.now()))){
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
