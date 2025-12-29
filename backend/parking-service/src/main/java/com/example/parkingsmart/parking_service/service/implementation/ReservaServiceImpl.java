package com.example.parkingsmart.parking_service.service.implementation;

import com.example.parkingsmart.parking_service.dto.EstadoReserva;
import com.example.parkingsmart.parking_service.dto.ReservaRequest;
import com.example.parkingsmart.parking_service.entity.Cochera;
import com.example.parkingsmart.parking_service.entity.Reserva;
import com.example.parkingsmart.parking_service.repository.CocheraRepository;
import com.example.parkingsmart.parking_service.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl {
    private final CocheraRepository cocheraRepo;
    private final ReservaRepository reservaRepo;

    public Reserva crearReserva(ReservaRequest req, String userId) {
        Cochera c = cocheraRepo.findById(req.cocheraId())
                .orElseThrow(() -> new RuntimeException("Cochera no existe"));

        if (!c.isDisponible())
            throw new RuntimeException("Cochera ocupada");

        c.setDisponible(false);
        cocheraRepo.save(c);

        Reserva r = new Reserva();
        r.setCochera(c);
        r.setUsuarioId(userId);
        r.setInicio(LocalDateTime.now());
        r.setFin(LocalDateTime.now().plusHours(req.horas()));
        r.setEstado(EstadoReserva.RESERVADA);

        return reservaRepo.save(r);
    }

    public List<Cochera> listarCocheras() {
        return cocheraRepo.findAll();
    }

    public List<Reserva> reservasDeUsuario(String userId) {
        return reservaRepo.findByUsuarioId(userId);
    }

    public void cancelar(String id) {
        Reserva r = reservaRepo.findById(id).orElseThrow();
        r.setEstado(EstadoReserva.CANCELADA);
        r.getCochera().setDisponible(true);
        reservaRepo.save(r);
    }
}
