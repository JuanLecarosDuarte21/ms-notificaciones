
package com.tiendapc.notificaciones.service;

import com.tiendapc.notificaciones.model.Notificacion;
import com.tiendapc.notificaciones.repository.NotificacionRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public NotificacionService(
            NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    // Crear una notificación
    public Notificacion crearNotificacion(
            Notificacion notificacion) {

        notificacion.setId(null);
        notificacion.setEstado("PENDIENTE");
        notificacion.setFechaCreacion(null);

        return notificacionRepository.save(notificacion);
    }

    // Listar todas las notificaciones
    public List<Notificacion> listarNotificaciones() {
        return notificacionRepository.findAll();
    }

    // Buscar una notificación por ID
    public Optional<Notificacion> buscarPorId(Long id) {
        return notificacionRepository.findById(id);
    }

    // Buscar notificaciones por destinatario
    public List<Notificacion> buscarPorDestinatario(
            String destinatario) {

        return notificacionRepository.findByDestinatario(
                destinatario);
    }

    // Buscar notificaciones por tipo
    public List<Notificacion> buscarPorTipo(String tipo) {

        return notificacionRepository.findByTipo(
                tipo.trim().toUpperCase());
    }

    // Buscar notificaciones por estado
    public List<Notificacion> buscarPorEstado(String estado) {

        return notificacionRepository.findByEstado(
                estado.trim().toUpperCase());
    }

    // Buscar por destinatario y estado
    public List<Notificacion> buscarPorDestinatarioYEstado(
            String destinatario, String estado) {

        return notificacionRepository
                .findByDestinatarioAndEstado(
                        destinatario,
                        estado.trim().toUpperCase());
    }

    // Actualizar el estado de una notificación
    public Optional<Notificacion> actualizarEstado(
            Long id, String estado) {

        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException(
                    "El estado no puede estar vacío");
        }

        return notificacionRepository.findById(id)
                .map(notificacion -> {

                    notificacion.setEstado(
                            estado.trim().toUpperCase());

                    return notificacionRepository.save(
                            notificacion);
                });
    }

    // Eliminar una notificación
    public boolean eliminarNotificacion(Long id) {

        if (!notificacionRepository.existsById(id)) {
            return false;
        }

        notificacionRepository.deleteById(id);
        return true;
    }
}
