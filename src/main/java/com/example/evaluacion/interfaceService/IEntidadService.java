package com.example.evaluacion.interfaceService;

import java.util.List;
import java.util.Optional;

import com.example.evaluacion.models.entidad;

public interface IEntidadService {
    
    public String save(entidad entidad);
    public List<entidad> findAll();

    //filtros
    public List<entidad> filtroEntidad(String filtro);
    public List<entidad> tareasCercanasAlVencimiento();
    public List<entidad> tareasVencidas();
    public List<entidad> tareasParaActualizarEstado();

    public Optional<entidad> findOne(String id);
    public int delete(String id);
}
