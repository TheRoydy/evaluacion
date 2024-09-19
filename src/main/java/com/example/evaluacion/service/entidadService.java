package com.example.evaluacion.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evaluacion.interfaceService.IEntidadService;
import com.example.evaluacion.interfaces.IEntidad;
import com.example.evaluacion.models.entidad;

@Service
public class entidadService implements IEntidadService {

     @Autowired
    private IEntidad data;

    @Override
    public String save(entidad entidad){
        data.save(entidad);
        return entidad.getId();
    }

    @Override
    public List<entidad> findAll(){
        List<entidad> listaEntidad=(List<entidad>) data.findAll();
        return listaEntidad;
    }

    //Filtros
    @Override
    public List<entidad> filtroEntidad(String filtro){
        List<entidad> listaEntidad = data.filtroEntidad(null);
        return listaEntidad;
    }

    //filtros
    @Override
    public List<entidad> tareasCercanasAlVencimiento() {
        List<entidad> listaEntidad = data.tareasCercanasAlVencimiento();
        return listaEntidad;
    }
    //filtros
    @Override
    public List<entidad> tareasVencidas() {
        List<entidad> listaEntidad = data.tareasVencidas();
        return listaEntidad;
    }
    //filtros
    @Override
    public List<entidad> tareasParaActualizarEstado() {
        List<entidad> listaEntidad = data.tareasParaActualizarEstado();
        return listaEntidad;
    }


    @Override
    public Optional<entidad> findOne(String id){
        Optional<entidad> entidad = data.findById(id);
        return entidad;
    }

    @Override
    public int delete(String id){
        data.deleteById(id);
        return 1;
    }
    
}
