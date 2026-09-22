package com.proyectocanchita.canchitasanmartin.cancha.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyectocanchita.canchitasanmartin.cancha.DTO.CanchaRequestDTO;
import com.proyectocanchita.canchitasanmartin.cancha.DTO.CanchaResponseDTO;
import com.proyectocanchita.canchitasanmartin.cancha.model.Cancha;
import com.proyectocanchita.canchitasanmartin.cancha.repository.CanchaRepository;
import com.proyectocanchita.canchitasanmartin.common.exception.RecursoNoEncontradoException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CanchaService {
    private final CanchaRepository canchaRepository;
    
    public CanchaResponseDTO buscarPorId(Integer id){
		Cancha cancha = canchaRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Cancha con id: "+id+" no encontrada"));
        CanchaResponseDTO respuestaCanchaDTO = new CanchaResponseDTO(cancha.getId(),
        cancha.getNombre(),
        cancha.getCapacidad() ,
        cancha.getPrecioHoraDia(),
        cancha.getPrecioHoraNoche());
        return respuestaCanchaDTO;
    }
    public List<CanchaResponseDTO> listarTodas(){
        List<Cancha> cancha = canchaRepository.findAll();
        ArrayList<CanchaResponseDTO> listaCancha = new ArrayList<>();
        for (Cancha datosCancha : cancha) {
            CanchaResponseDTO datosCanchaFinal = new CanchaResponseDTO(
                datosCancha.getId(),
                datosCancha.getNombre() ,
                datosCancha.getCapacidad() ,
                datosCancha.getPrecioHoraDia() ,
                datosCancha.getPrecioHoraNoche()
            );
            listaCancha.add(datosCanchaFinal);
        }
        return  listaCancha;
    }

    public CanchaResponseDTO crearCancha(CanchaRequestDTO canchaRequestDto){
        Cancha nuevaCancha = new Cancha();
        nuevaCancha.setNombre(canchaRequestDto.getNombre());
        nuevaCancha.setCapacidad(canchaRequestDto.getCapacidad());
        nuevaCancha.setPrecioHoraDia(canchaRequestDto.getPrecioHoraDia());
        nuevaCancha.setPrecioHoraNoche(canchaRequestDto.getPrecioHoraNoche());
        Cancha canchaGuardada = canchaRepository.save(nuevaCancha);
        CanchaResponseDTO respuestaCancha = new CanchaResponseDTO(
            canchaGuardada.getId(),
            canchaGuardada.getNombre() ,
            canchaGuardada.getCapacidad() ,
            canchaGuardada.getPrecioHoraDia() ,
            canchaGuardada.getPrecioHoraNoche()
            );
            return  respuestaCancha;
    }
    public CanchaResponseDTO actualizarCancha(CanchaRequestDTO canchaRequestDTO, Integer id){
        Cancha canchaExistente = canchaRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Cancha con id: "+id+" no encontrada"));
        canchaExistente.setNombre(canchaRequestDTO.getNombre());
        canchaExistente.setCapacidad(canchaRequestDTO.getCapacidad());
        canchaExistente.setPrecioHoraDia(canchaRequestDTO.getPrecioHoraDia());
        canchaExistente.setPrecioHoraNoche(canchaRequestDTO.getPrecioHoraNoche());
        
        Cancha canchaActualizadaDatos = canchaRepository.save(canchaExistente);
        CanchaResponseDTO respuestaCanchaActualizada = new CanchaResponseDTO(
            canchaActualizadaDatos.getId(),
            canchaActualizadaDatos.getNombre(),
            canchaActualizadaDatos.getCapacidad(),
            canchaActualizadaDatos.getPrecioHoraDia(),
            canchaActualizadaDatos.getPrecioHoraNoche()); 
            
    return respuestaCanchaActualizada;
    }
    public void eliminarCancha(Integer id){
        canchaRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Cancha con id: "+id+" no encontrado"));
        canchaRepository.deleteById(id);
    }
}

