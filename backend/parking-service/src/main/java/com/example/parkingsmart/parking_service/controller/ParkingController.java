package com.example.parkingsmart.parking_service.controller;

import com.example.parkingsmart.parking_service.dto.ReservaRequest;
import com.example.parkingsmart.parking_service.entity.Cochera;
import com.example.parkingsmart.parking_service.entity.Reserva;
import com.example.parkingsmart.parking_service.service.implementation.ReservaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ReservaServiceImpl service;

    @GetMapping("/cocheras")
    public List<Cochera> all() {
        return service.listarCocheras();
    }

    @PostMapping("/reservas")
    public Reserva reservar(@RequestBody ReservaRequest req,
                            @AuthenticationPrincipal Jwt principal) {
        String userId = principal.getSubject();
        return service.crearReserva(req, userId);
    }

    @GetMapping("/reservas/mias")
    public List<Reserva> mias(@AuthenticationPrincipal Jwt principal) {
        return service.reservasDeUsuario(principal.getSubject());
    }

    @DeleteMapping("/reservas/{id}")
    public void cancelar(@PathVariable String id) {
        service.cancelar(id);
    }
}
