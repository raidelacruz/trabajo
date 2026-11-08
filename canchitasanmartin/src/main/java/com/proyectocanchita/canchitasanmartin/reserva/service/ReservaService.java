package com.proyectocanchita.canchitasanmartin.reserva.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyectocanchita.canchitasanmartin.cancha.repository.CanchaRepository;
import com.proyectocanchita.canchitasanmartin.cliente.repository.ClienteRepository;
import com.proyectocanchita.canchitasanmartin.common.exception.RecursoNoEncontradoException;
import com.proyectocanchita.canchitasanmartin.reserva.Dto.ReservaRequestDto;
import com.proyectocanchita.canchitasanmartin.reserva.Dto.ReservaResponseDto;
import com.proyectocanchita.canchitasanmartin.reserva.model.Reserva;
import com.proyectocanchita.canchitasanmartin.reserva.repository.ReservaRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ReservaService {
    private final ReservaRepository reservaRepository;
        private final ClienteRepository clienteRepository;
    private final CanchaRepository canchaRepository;



    public ReservaResponseDto mapearAResponseDto(Reserva reserva) {
        String nombreCompletoCliente=reserva.getCliente().getNombre()+" "+reserva.getCliente().getApellido();
        return new ReservaResponseDto(
            reserva.getId(),
         reserva.getCliente().getId(),
          nombreCompletoCliente, reserva.getCancha().getId(),
        reserva.getCancha().getNombre(),reserva.getFechaCancha(),
        reserva.getHoraInicio(),
        reserva.getHoraFin(),
        reserva.getEstado(),reserva.getMontoTotal(),
        reserva.getFechaCreacion());
    }

    public ReservaResponseDto buscarPorId(Integer id){
        Reserva reserva = reservaRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Reserva con id: "+id+" no encontrado"));
        return mapearAResponseDto(reserva);
    }

    public List<ReservaResponseDto> listaTodas(){
        List<Reserva> reservas = reservaRepository.findAll();
        ArrayList<ReservaResponseDto> listaReserva = new ArrayList<>();
        for (Reserva datosReserva : reservas) {
            ReservaResponseDto reservaResponseDto = mapearAResponseDto(datosReserva);
            listaReserva.add(reservaResponseDto);
        }
        return listaReserva;
    } 

    public List<ReservaResponseDto> listarPorFecha(LocalDate fecha){
        List<Reserva> reservas = reservaRepository.findByFechaCancha(fecha);
        ArrayList<ReservaResponseDto> listaReserva = new ArrayList<>();
        for (Reserva datosReserva : reservas) {
            ReservaResponseDto reservaResponseDto = mapearAResponseDto(datosReserva);
            listaReserva.add(reservaResponseDto);
        }
        return listaReserva;
    } 
    public ReservaResponseDto crearReserva(ReservaRequestDto dto){
        
    }
}
