package com.example.evaluacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evaluacion.interfaceService.IEntidadService;
import com.example.evaluacion.models.entidad;
import com.example.evaluacion.service.emailService;


@RequestMapping("/api/v1/entidad")
@RestController
@CrossOrigin
public class entidadController {

    @Autowired
    private IEntidadService entidadService;

    @Autowired
    private emailService emailService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody entidad entidad){

        List<entidad> entidad2 = entidadService.filtroEntidad(entidad.getTitle());
        if (!entidad2.isEmpty()) {
            return new ResponseEntity<>("El titulo ya existe", HttpStatus.BAD_REQUEST);   
        }

        if (entidad.getTitle().equals("")) {
            return new ResponseEntity<>("El tipo de documento es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (entidad.getDue_date().equals("")) {
            return new ResponseEntity<>("El número de documento es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (entidad.getAssigned_to().equals("")) {
            return new ResponseEntity<>("La fecha de nacimiento es obligatorio", HttpStatus.BAD_REQUEST);
        }        

        if (entidad.getStatus().equals("")) {
            return new ResponseEntity<>("La contraseña es obligatorio", HttpStatus.BAD_REQUEST);
        }

        entidadService.save(entidad);
        emailService.enviarCorreoTarea(entidad.getAssigned_to(), entidad.getTitle());
        return new ResponseEntity<>(entidad,HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        var listaEntidad = entidadService.findAll();
        return new ResponseEntity<>(listaEntidad, HttpStatus.OK);
    }

    //Filtros
    @GetMapping("/busquedaFiltro/{filtro}")
    public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
        var listaEntidad = entidadService.filtroEntidad(filtro);
        return new ResponseEntity<>(listaEntidad, HttpStatus.OK);
    }
    //filtros
    @GetMapping("/tareasCercanasAlVencimiento")
    public ResponseEntity<Object> findTaerasCercanas() {
        var listaEntidad = entidadService.tareasCercanasAlVencimiento();
        return new ResponseEntity<>(listaEntidad, HttpStatus.OK);
    }
    //Filtros
    @GetMapping("/tareasVencidas")
    public ResponseEntity<Object> findTareasVencidas() {
        var listaEntidad = entidadService.tareasVencidas();
        return new ResponseEntity<>(listaEntidad, HttpStatus.OK);
    }

    //   //Filtros
    //   @GetMapping("/tareasParaActualizarEstado")
    //   public ResponseEntity<Object> findTareasActualizar() {
    //       var listaEntidad = entidadService.tareasParaActualizarEstado();
    //       return new ResponseEntity<>(listaEntidad, HttpStatus.OK);
    //   }

    @GetMapping("/{idEntidad}")
    public ResponseEntity<Object> findOne(@PathVariable("idEntidad") String id){
        var registro = entidadService.findOne(id);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @DeleteMapping("/{idEntidad}")
    public ResponseEntity<Object> delete(@PathVariable("idEntidad") String id){
        entidadService.delete(id);
        return new ResponseEntity<>("Tarea eliminada con éxito", HttpStatus.OK);
    }

    @PutMapping("/{idEntidad}")
    public ResponseEntity<Object> update(@PathVariable("idEntidad") String id, @RequestBody entidad entidadUpdate){
        var entidad = entidadService.findOne(id).get();
        if (entidad != null) {
            entidad.setTitle(entidadUpdate.getTitle());
            entidad.setDue_date(entidadUpdate.getDue_date());
            entidad.setAssigned_to(entidadUpdate.getAssigned_to());
            entidad.setStatus(entidadUpdate.getStatus());

            entidadService.save(entidad);
            return new ResponseEntity<>("Guardado", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Error regitro no encontrado", HttpStatus.BAD_REQUEST);
        }
    } 
    
}
