package com.example.evaluacion.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.evaluacion.models.entidad;

@Repository
public interface IEntidad extends CrudRepository<entidad, String> {

    @Query ("SELECT e FROM entidad e WHERE e.Title LIKE %?1%")
    List<entidad> filtroEntidad(String filtro);
    
    @Query("SELECT e FROM entidad e WHERE t.status = 'pendiente' " +
           "AND (e.due_date = CURRENT_DATE + 5 OR e.due_date = CURRENT_DATE + 1)")
    List<entidad> tareasCercanasAlVencimiento();

    @Query("SELECT t FROM entidad t WHERE t.status = 'vencida' " +
    "AND e.due_date < CURRENT_DATE")
    List<entidad> tareasVencidas();

    @Query("SELECT e FROM entidad t WHERE e.status != 'vencida' " +
           "AND t.due_date < CURRENT_DATE")
    List<entidad> tareasParaActualizarEstado();
}
