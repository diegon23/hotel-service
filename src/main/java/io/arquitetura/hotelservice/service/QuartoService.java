package io.arquitetura.hotelservice.service;

import io.arquitetura.hotelservice.entity.Quarto;
import io.arquitetura.hotelservice.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;

    private final ReservaService reservaService;

    private final QuartoLimpezaService quartoLimpezaService;

    @Autowired
    private QuartoService(final QuartoRepository quartoRepository,
                          final ReservaService reservaService,
                          final QuartoLimpezaService quartoLimpezaService){
        this.quartoRepository = quartoRepository;
        this.reservaService = reservaService;
        this.quartoLimpezaService = quartoLimpezaService;
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

    public List<Quarto> consultarQuartosDisponiveis(){
        List<Quarto> quartos = new ArrayList<>();
        quartoRepository.findAll().forEach(quartos::add);

        return quartos.stream()
                .filter(q -> reservaService.findByQuarto(q.getId()).isEmpty())
                .filter(q -> quartoLimpezaService.findByQuarto(q.getId()).isEmpty())
                .collect(Collectors.toList());
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
