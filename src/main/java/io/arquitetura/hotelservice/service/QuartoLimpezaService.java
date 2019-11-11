package io.arquitetura.hotelservice.service;

import io.arquitetura.hotelservice.entity.QuartoLimpeza;
import io.arquitetura.hotelservice.repository.QuartoLimpezaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuartoLimpezaService {

    private final QuartoLimpezaRepository quartoLimpezaRepository;

    @Autowired
    private QuartoLimpezaService(final QuartoLimpezaRepository quartoLimpezaRepository){
        this.quartoLimpezaRepository = quartoLimpezaRepository;
    }

    public <S extends QuartoLimpeza> S save(S entity) {
        return quartoLimpezaRepository.save(entity);
    }

    public <S extends QuartoLimpeza> Iterable<S> saveAll(Iterable<S> entities) {
        return quartoLimpezaRepository.saveAll(entities);
    }

    public Optional<QuartoLimpeza> findById(Long aLong) {
        return quartoLimpezaRepository.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return quartoLimpezaRepository.existsById(aLong);
    }

    public Iterable<QuartoLimpeza> findAll() {
        return quartoLimpezaRepository.findAll();
    }

    public Iterable<QuartoLimpeza> findAllById(Iterable<Long> longs) {
        return quartoLimpezaRepository.findAllById(longs);
    }

    public long count() {
        return quartoLimpezaRepository.count();
    }

    public void deleteById(Long id) {
        quartoLimpezaRepository.deleteById(id);
    }

    public void delete(QuartoLimpeza entity) {
        quartoLimpezaRepository.delete(entity);
    }

    public void deleteAll(Iterable<? extends QuartoLimpeza> entities) {
        quartoLimpezaRepository.deleteAll(entities);
    }

    public void deleteAll() {
        quartoLimpezaRepository.deleteAll();
    }
}
