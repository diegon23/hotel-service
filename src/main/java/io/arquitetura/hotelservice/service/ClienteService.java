package io.arquitetura.hotelservice.service;

import io.arquitetura.hotelservice.entity.Cliente;
import io.arquitetura.hotelservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    private ClienteService(final ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public <S extends Cliente> S save(S entity) {
        return clienteRepository.save(entity);
    }

    public <S extends Cliente> Iterable<S> saveAll(Iterable<S> entities) {
        return clienteRepository.saveAll(entities);
    }

    public Optional<Cliente> findById(Long aLong) {
        return clienteRepository.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return clienteRepository.existsById(aLong);
    }

    public Iterable<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Iterable<Cliente> findAllById(Iterable<Long> longs) {
        return clienteRepository.findAllById(longs);
    }

    public long count() {
        return clienteRepository.count();
    }

    public void deleteById(Long aLong) {
        clienteRepository.deleteById(aLong);
    }

    public void delete(Cliente entity) {
        clienteRepository.delete(entity);
    }

    public void deleteAll(Iterable<? extends Cliente> entities) {
        clienteRepository.deleteAll(entities);
    }

    public void deleteAll() {
        clienteRepository.deleteAll();
    }
}
