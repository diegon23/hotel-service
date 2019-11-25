package io.arquitetura.hotelservice.service;

import io.arquitetura.hotelservice.entity.Cliente;
import io.arquitetura.hotelservice.entity.Quarto;
import io.arquitetura.hotelservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<Cliente> findById(String s) {
        return clienteRepository.findById(s);
    }

    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        clienteRepository.findAll().forEach(clientes::add);

        return clientes;
    }

    public void deleteById(String s) {
        clienteRepository.deleteById(s);
    }

    public void delete(Cliente entity) {
        clienteRepository.delete(entity);
    }
}
