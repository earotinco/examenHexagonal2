package com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.ports.in;

import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.dto.EmpleadoDto;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.response.ResponseBase;

import java.util.List;

public interface EmpleadoServiceIn {

    /*a. /crear
b. /buscar/{numDoc}
c. /todos/ -> Debe filtrar solo por estad activo (true)
d. /actualizar/ -> Solo debe permitir actualizar (Edad, cargo, salario, teléfono,
correo, departamento, telefono)
e. /eliminar/ -> Debe aplicar eliminado lógico (false)*/


    ResponseBase<EmpleadoDto> crearEmpleadoIn(EmpleadoDto empleadoDto);
    EmpleadoDto buscarEmpleadoIn(Long id);
    EmpleadoDto buscarEmpleadoDniIn(String dni);
    List<EmpleadoDto> obtenerTodosXEstadoIn(boolean estado);
    EmpleadoDto actualizarEmpleadoIn(Long id, EmpleadoDto empleadoDto);
    void eliminarEmpleadoLogicoIn(Long id);


}
