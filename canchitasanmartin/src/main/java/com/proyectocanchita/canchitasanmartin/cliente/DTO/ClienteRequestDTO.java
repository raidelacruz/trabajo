package com.proyectocanchita.canchitasanmartin.cliente.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClienteRequestDTO {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @Pattern(regexp = "^[0-9]{8}$", message = "El DNI no puede contener letras ni símbolos") @Size(max = 15)
    private String dni;
     @Pattern(regexp = "^[0-9]{9}$", message = "El telefono solo puede contener numeros")
    @NotBlank @Size(max = 20)
    private String telefono; 
}
