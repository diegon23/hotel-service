package io.arquitetura.hotelservice.repository;

import io.arquitetura.hotelservice.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}