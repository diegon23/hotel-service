package io.arquitetura.hotelservice.service;

import io.arquitetura.hotelservice.entity.Quarto;
import io.arquitetura.hotelservice.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;

    @Autowired
    private QuartoService(final QuartoRepository quartoRepository){
        this.quartoRepository = quartoRepository;
    }

    public <S extends Quarto> S save(S entity) {
        return quartoRepository.save(entity);
    }

    public <S extends Quarto> Iterable<S> saveAll(Iterable<S> entities) {
        return quartoRepository.saveAll(entities);
    }

    public Optional<Quarto> findById(Long aLong) {
        return quartoRepository.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return quartoRepository.existsById(aLong);
    }

    public Iterable<Quarto> findAll() {
        return quartoRepository.findAll();
    }

    public Iterable<Quarto> findAllById(Iterable<Long> longs) {
        return quartoRepository.findAllById(longs);
    }

    public long count() {
        return quartoRepository.count();
    }

    public void deleteById(Long aLong) {
        quartoRepository.deleteById(aLong);
    }

    public void delete(Quarto entity) {
        quartoRepository.delete(entity);
    }

    public void deleteAll(Iterable<? extends Quarto> entities) {
        quartoRepository.deleteAll(entities);
    }

    public void deleteAll() {
        quartoRepository.deleteAll();
    }
}
