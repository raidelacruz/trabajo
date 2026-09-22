package com.proyectocanchita.canchitasanmartin.cancha.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyectocanchita.canchitasanmartin.cancha.DTO.CanchaRequestDTO;
import com.proyectocanchita.canchitasanmartin.cancha.DTO.CanchaResponseDTO;
import com.proyectocanchita.canchitasanmartin.cancha.service.CanchaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/canchas") 
public class CanchaController {
    private final CanchaService canchaService;

    @GetMapping 
    public List<CanchaResponseDTO> listarTodas(){
        return  canchaService.listarTodas();
    }

    @GetMapping("{/id}")
    public CanchaResponseDTO buscarPorId(@PathVariable Integer id){
        return canchaService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) 
    public CanchaResponseDTO crearCancha(@Valid  @RequestBody CanchaRequestDTO datosCancha){
    return canchaService.crearCancha(datosCancha);}

    @PutMapping("/{id}")
    public CanchaResponseDTO actualizarCancha(@Valid @RequestBody CanchaRequestDTO datosCanchaActualizar, @PathVariable Integer id){
        return canchaService.actualizarCancha(datosCanchaActualizar, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCancha(@PathVariable Integer id){
        canchaService.eliminarCancha(id);
        return ResponseEntity.noContent().build();
    }
}

