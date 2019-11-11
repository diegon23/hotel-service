package io.arquitetura.hotelservice.service;

import io.arquitetura.hotelservice.entity.Reserva;
import io.arquitetura.hotelservice.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public <S extends Reserva> Iterable<S> saveAll(Iterable<S> entities) {
        return reservaRepository.saveAll(entities);
    }

    public Optional<Reserva> findById(Long aLong) {
        return reservaRepository.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return reservaRepository.existsById(aLong);
    }

    public Iterable<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Iterable<Reserva> findAllById(Iterable<Long> longs) {
        return reservaRepository.findAllById(longs);
    }

    public long count() {
        return reservaRepository.count();
    }

    public void deleteById(Long aLong) {
        reservaRepository.deleteById(aLong);
    }

    public void delete(Reserva entity) {
        reservaRepository.delete(entity);
    }

    public void deleteAll(Iterable<? extends Reserva> entities) {
        reservaRepository.deleteAll(entities);
    }

    public void deleteAll() {
        reservaRepository.deleteAll();
    }
}
