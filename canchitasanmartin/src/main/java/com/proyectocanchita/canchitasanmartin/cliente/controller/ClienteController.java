package com.proyectocanchita.canchitasanmartin.cliente.controller;

import org.springframework.web.bind.annotation.RestController;

import com.proyectocanchita.canchitasanmartin.cliente.DTO.ClienteRequestDTO;
import com.proyectocanchita.canchitasanmartin.cliente.DTO.ClienteResponseDTO;
import com.proyectocanchita.canchitasanmartin.cliente.service.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<ClienteResponseDTO> listarTodos()    {
        return clienteService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ClienteResponseDTO buscarPorId(@PathVariable Long id){
    
        return clienteService.buscarPorId(id);
    }

    @PostMapping 
    public  ClienteResponseDTO crearCliente(@Valid  @RequestBody ClienteRequestDTO datosCliente){
        return clienteService.crearCliente(datosCliente);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO actualizarCliente(@Valid @RequestBody ClienteRequestDTO datosClienteActualizado,@PathVariable Long id) {
        
        return clienteService.actualizarCliente(datosClienteActualizado, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id){
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();

    }
}
