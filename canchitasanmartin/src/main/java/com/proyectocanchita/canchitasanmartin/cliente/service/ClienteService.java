package com.proyectocanchita.canchitasanmartin.cliente.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.proyectocanchita.canchitasanmartin.cliente.DTO.ClienteRequestDTO;
import com.proyectocanchita.canchitasanmartin.cliente.DTO.ClienteResponseDTO;
import com.proyectocanchita.canchitasanmartin.cliente.model.Cliente;
import com.proyectocanchita.canchitasanmartin.cliente.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
    
    public ClienteResponseDTO buscarPorId(Long id){
       Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente con id: "+id+" no encontrado"));
        ClienteResponseDTO respuestaDTO = new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNombre(),cliente.getApellido(),
            cliente.getDni(),cliente.getTelefono(),cliente.getFechaRegistro()
        );
        return respuestaDTO;
    }
    public List<ClienteResponseDTO> listarTodos(){
        List<Cliente> clientes = clienteRepository.findAll();
        ArrayList<ClienteResponseDTO> listaCliente = new ArrayList<>();
        for ( Cliente datos : clientes) {
            ClienteResponseDTO clientesdato = new ClienteResponseDTO(
            datos.getId(),
            datos.getNombre(),datos.getApellido(),
            datos.getDni(),datos.getTelefono(),datos.getFechaRegistro()
        );
            listaCliente.add(clientesdato);
        }
        return listaCliente;
    }

    public ClienteResponseDTO crearCliente(ClienteRequestDTO datosCliente){
        if (datosCliente.getDni() != null) {
            if (clienteRepository.findByDni(datosCliente.getDni()).isPresent()) {
                throw new RuntimeException("EL DNI: "+datosCliente.getDni()+" ya esta usado");
            }
        }
        Cliente nuevocliente = new Cliente();
            nuevocliente.setNombre(datosCliente.getNombre());
            nuevocliente.setApellido(datosCliente.getApellido());
            nuevocliente.setDni(datosCliente.getDni());
            nuevocliente.setTelefono(datosCliente.getTelefono());

        Cliente clienteGuardado = clienteRepository.save(nuevocliente);

        ClienteResponseDTO respuesta = new ClienteResponseDTO(
            clienteGuardado.getId(),
            clienteGuardado.getNombre(),
            clienteGuardado.getApellido(),
            clienteGuardado.getDni(),
            clienteGuardado.getTelefono(),
            clienteGuardado.getFechaRegistro());
    return respuesta;
            
    }

    public ClienteResponseDTO actualizarCliente(ClienteRequestDTO datosClienteActualizado, Long id){
       Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente con id: "+id+" no encontrado"));
        clienteExistente.setNombre(datosClienteActualizado.getNombre());
        clienteExistente.setApellido(datosClienteActualizado.getApellido());
        clienteExistente.setDni(datosClienteActualizado.getDni());
        clienteExistente.setTelefono(datosClienteActualizado.getTelefono());
        Cliente clienteActualizado = clienteRepository.save(clienteExistente);
        ClienteResponseDTO respuestaCliente = new ClienteResponseDTO(
            
            clienteActualizado.getId(),
            clienteActualizado.getNombre(), 
            clienteActualizado.getApellido(),
            clienteActualizado.getDni(), 
            clienteActualizado.getTelefono(),
            clienteActualizado.getFechaRegistro() 
        );
        return respuestaCliente;
    }

    public void eliminarCliente(Long id){
        clienteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cliente con id: "+id+" no encontrado"));
        clienteRepository.deleteById(id);
    }
}
