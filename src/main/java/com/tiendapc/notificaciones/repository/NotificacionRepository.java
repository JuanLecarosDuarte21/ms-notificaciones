
package com.tiendapc.notificaciones.repository;

import com.tiendapc.notificaciones.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository
        extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByDestinatario(String destinatario);

    List<Notificacion> findByTipo(String tipo);

    List<Notificacion> findByEstado(String estado);

    List<Notificacion> findByDestinatarioAndEstado(
            String destinatario, String estado);
}
