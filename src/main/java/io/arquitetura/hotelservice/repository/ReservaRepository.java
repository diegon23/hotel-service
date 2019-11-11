package io.arquitetura.hotelservice.repository;

import io.arquitetura.hotelservice.entity.Reserva;
import org.springframework.data.repository.CrudRepository;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {

}