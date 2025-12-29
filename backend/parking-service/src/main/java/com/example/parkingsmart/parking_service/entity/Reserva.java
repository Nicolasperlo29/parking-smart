package com.example.parkingsmart.parking_service.entity;

import com.example.parkingsmart.parking_service.dto.EstadoReserva;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "cochera_id")
    private Cochera cochera;

    private String usuarioId; // viene desde JWT → sub o userId

    private LocalDateTime inicio;
    private LocalDateTime fin;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;
}
