package com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.ports.out;

import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.dto.EmpleadoDto;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.response.ResponseBase;

import java.util.List;


public interface EmpleadoServiceOut {

    ResponseBase<EmpleadoDto> crearEmpleadoOut(EmpleadoDto empleadoDto);
    EmpleadoDto buscarEmpleadoOut(Long id);
    EmpleadoDto buscarEmpleadoDniOut(String dni);
    List<EmpleadoDto> obtenerTodosXEstadoOut(boolean estado);
    EmpleadoDto actualizarEmpleadoOut(Long id, EmpleadoDto empleadoDto);
    void eliminarEmpleadoLogicoOut(Long id);
}

