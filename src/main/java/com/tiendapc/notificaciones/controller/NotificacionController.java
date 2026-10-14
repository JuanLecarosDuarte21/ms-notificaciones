
package com.tiendapc.notificaciones.controller;

import com.tiendapc.notificaciones.model.Notificacion;
import com.tiendapc.notificaciones.service.NotificacionService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    public NotificacionController(
            NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    // Crear una notificación
    @PostMapping
    public ResponseEntity<Notificacion> crearNotificacion(
            @Valid @RequestBody Notificacion notificacion) {

        Notificacion nuevaNotificacion =
                notificacionService.crearNotificacion(notificacion);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaNotificacion);
    }

    // Listar todas las notificaciones
    @GetMapping
    public List<Notificacion> listarNotificaciones() {
        return notificacionService.listarNotificaciones();
    }

    // Buscar una notificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> buscarPorId(
            @PathVariable Long id) {

        return notificacionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    // Buscar notificaciones por destinatario
    @GetMapping("/destinatario/{destinatario}")
    public List<Notificacion> buscarPorDestinatario(
            @PathVariable String destinatario) {

        return notificacionService
                .buscarPorDestinatario(destinatario);
    }

    // Buscar notificaciones por tipo
    @GetMapping("/tipo/{tipo}")
    public List<Notificacion> buscarPorTipo(
            @PathVariable String tipo) {

        return notificacionService.buscarPorTipo(tipo);
    }

    // Buscar notificaciones por estado
    @GetMapping("/estado/{estado}")
    public List<Notificacion> buscarPorEstado(
            @PathVariable String estado) {

        return notificacionService.buscarPorEstado(estado);
    }

    // Buscar por destinatario y estado
    @GetMapping("/destinatario/{destinatario}/estado/{estado}")
    public List<Notificacion> buscarPorDestinatarioYEstado(
            @PathVariable String destinatario,
            @PathVariable String estado) {

        return notificacionService
                .buscarPorDestinatarioYEstado(
                        destinatario, estado);
    }

    // Actualizar el estado de una notificación
    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(
            @PathVariable Long id,
            @RequestBody ActualizarEstadoRequest request) {

        try {
            return notificacionService
                    .actualizarEstado(id, request.estado())
                    .<ResponseEntity<?>>map(ResponseEntity::ok)
                    .orElseGet(() ->
                            ResponseEntity.notFound().build());

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // Eliminar una notificación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(
            @PathVariable Long id) {

        if (notificacionService.eliminarNotificacion(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    public record ActualizarEstadoRequest(String estado) {
    }
}
