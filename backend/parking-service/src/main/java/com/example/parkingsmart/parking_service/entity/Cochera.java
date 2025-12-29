package com.example.parkingsmart.parking_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cocheras")
@Data
public class Cochera {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Integer numero;

    private boolean disponible = true;
}
