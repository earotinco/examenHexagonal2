package com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.impl;

import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.constants.Constans;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.dto.EmpleadoDto;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.response.ResponseBase;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.ports.in.EmpleadoServiceIn;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.ports.out.EmpleadoServiceOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmpleadoServiceImpl implements EmpleadoServiceIn {


    private final EmpleadoServiceOut empleadoServiceOut;

    private String serviceName ="EmpleadoServiceInImpl";

    @Override
    public ResponseBase<EmpleadoDto> crearEmpleadoIn(EmpleadoDto empleadoDto) {

        log.info("Registrando persona con DNI: {}", empleadoDto.getNumDoc());
        String nameMethod = "crearEmpleadoIn";
        log.info(Constans.LOG_INICIO+serviceName, nameMethod);


        if(empleadoDto==null){
            log.info(Constans.LOG_ERROR+ serviceName, nameMethod);
            throw new IllegalArgumentException("No hay datos del Empleado para registrar");
        }

        log.info(Constans.LOG_FIN+ serviceName, nameMethod);

        return empleadoServiceOut.crearEmpleadoOut(empleadoDto);
    }

    @Override
    public EmpleadoDto buscarEmpleadoIn(Long id) {
        String nameMethod = "buscarEmpleadoIn";
        log.info(Constans.LOG_INICIO + serviceName, nameMethod);

        if (id == null || id <= 0) {
            log.error("El ID proporcionado no es válido: {}", id);
            throw new IllegalArgumentException("El ID del empleado no puede ser null ni menor o igual a 0");
        }


        EmpleadoDto empleadoDto = empleadoServiceOut.buscarEmpleadoOut(id);

        if (empleadoDto == null) {
            log.error("Empleado no encontrado para el ID: {}", id);
           // throw new NotFoundException("Empleado no encontrado con ID: " + id);
        }

        log.info(Constans.LOG_FIN + serviceName + ", Método: " + nameMethod);
        return empleadoDto;
    }

    @Override
    public EmpleadoDto buscarEmpleadoDniIn(String dni) {
        String nameMethod = "buscarEmpleadoDniIn";
        log.info(Constans.LOG_INICIO + serviceName, nameMethod);

        // Validar si el DNI está vacío o es nulo
        if (dni == null || dni.isEmpty()) {
            log.error("El DNI proporcionado está vacío o es nulo.");
            throw new IllegalArgumentException("El DNI no puede estar vacío.");
        }


        EmpleadoDto empleadoDto = empleadoServiceOut.buscarEmpleadoDniOut(dni);


        if (empleadoDto == null) {
            log.error("No se encontró un empleado con el DNI {}", dni);
            throw new IllegalArgumentException("No se encontró un empleado con el DNI " + dni);
        }

        log.info(Constans.LOG_FIN + serviceName, nameMethod);
        return empleadoDto;
    }

    @Override
    public List<EmpleadoDto> obtenerTodosXEstadoIn(boolean estado) {
        String nameMethod = "obtenerTodosXEstadoIn";
        log.info(Constans.LOG_INICIO + serviceName, nameMethod);

        List<EmpleadoDto> empleadosDtoList = empleadoServiceOut.obtenerTodosXEstadoOut(estado);

        log.info(Constans.LOG_FIN + serviceName + ", Método: " + nameMethod);
        return empleadosDtoList;
    }

    @Override
    public EmpleadoDto actualizarEmpleadoIn(Long id, EmpleadoDto empleadoDto) {
        String nameMethod = "actualizarEmpleadoIn";
        log.info(Constans.LOG_INICIO + serviceName, nameMethod);

        EmpleadoDto empleadoExistente = empleadoServiceOut.buscarEmpleadoOut(id);

        if (empleadoExistente == null) {
            log.error("Empleado con DNI {} no encontrado", id);
            //throw new NotFoundException("Empleado con DNI " + dni + " no encontrado.");
        }


        EmpleadoDto empleadoActualizado = empleadoServiceOut.actualizarEmpleadoOut(id, empleadoDto);

        log.info(Constans.LOG_FIN + serviceName + ", Método: " + nameMethod);
        return empleadoActualizado;
    }

    @Override
    public void eliminarEmpleadoLogicoIn(Long id) {
        String nameMethod = "eliminarEmpleadoLogicoIn";
        log.info(Constans.LOG_INICIO + serviceName, nameMethod);

        EmpleadoDto empleadoExistente = empleadoServiceOut.buscarEmpleadoOut(id);

        if (empleadoExistente == null) {
            log.error("Empleado con ID {} no encontrado", id);
           // throw new NotFoundException("Empleado con ID " + id + " no encontrado.");
    }

        empleadoServiceOut.eliminarEmpleadoLogicoOut(id);

        log.info(Constans.LOG_FIN + serviceName, nameMethod);

    }
}
