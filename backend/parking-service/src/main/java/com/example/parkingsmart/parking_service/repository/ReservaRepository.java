package com.example.parkingsmart.parking_service.repository;

import com.example.parkingsmart.parking_service.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, String> {
    List<Reserva> findByUsuarioId(String usuarioId);
}
