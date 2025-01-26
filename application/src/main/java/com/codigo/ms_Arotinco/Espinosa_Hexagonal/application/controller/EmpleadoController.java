package com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.controller;


import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.constants.Constans;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.dto.EmpleadoDto;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.response.ResponseBase;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.ports.in.EmpleadoServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/ExamenHexa")
@RequiredArgsConstructor
public class EmpleadoController {


    private final EmpleadoServiceIn empleadoServiceIn;

   /* @PostMapping("/{dni}")
    public ResponseEntity<EmpleadoDto> crearEmpleado(
            @PathVariable("dni") String dni){
        return ResponseEntity.ok(empleadoServiceIn.crearEmpleadoIn(dni));
    }*/


    //RESPUESTA CON RESPONSEBASE
    @PostMapping("/crear")
    public ResponseEntity<ResponseBase<EmpleadoDto>> crearEmpleado(@RequestBody EmpleadoDto empleadoDto) {

        try {

            ResponseBase<EmpleadoDto> respuesta = empleadoServiceIn.crearEmpleadoIn(empleadoDto);

            //  código 200
            if (respuesta.getCode() == Constans.CODIGO_OK_POST) {
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            } else {
                // error: codigo 409
                return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(new ResponseBase<>(Constans.CODIGO_NULL_POINTER, Constans.MENSAJE_NULL_POINTER), HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ResponseBase<>(Constans.CODIGO_RUNTIME_EXCEPTION, Constans.MENSAJE_RUNTIME_EXCEPTION), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBase<>(Constans.CODIGO_EXCEPTION, Constans.MENSAJE_EXCEPTION), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar empleado por DNI
    @GetMapping("/buscar/{dni}")
    public ResponseEntity<ResponseBase<EmpleadoDto>> buscarEmpleado(@PathVariable String dni) {
        try {
            EmpleadoDto empleadoDto = empleadoServiceIn.buscarEmpleadoDniIn(dni);
            if (empleadoDto != null) {
                ResponseBase<EmpleadoDto> response = new ResponseBase<>(Constans.CODIGO_OK_GET, Constans.MENSAJE_OK, Optional.of(empleadoDto));
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ResponseBase<EmpleadoDto> response = new ResponseBase<>(Constans.CODIGO_NULL_POINTER, Constans.MENSAJE_NULL_POINTER, Optional.empty());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (NullPointerException e) {
            ResponseBase<EmpleadoDto> response = new ResponseBase<>(Constans.CODIGO_NULL_POINTER, Constans.MENSAJE_NULL_POINTER, Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            ResponseBase<EmpleadoDto> response = new ResponseBase<>(Constans.CODIGO_RUNTIME_EXCEPTION, Constans.MENSAJE_RUNTIME_EXCEPTION, Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ResponseBase<EmpleadoDto> response = new ResponseBase<>(Constans.CODIGO_EXCEPTION, Constans.MENSAJE_EXCEPTION, Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todos los empleados por estado (TRUE)
    @GetMapping("/todos")
    public ResponseEntity<ResponseBase<List<EmpleadoDto>>> obtenerTodosPorEstado() {
        try {
            List<EmpleadoDto> empleados = empleadoServiceIn.obtenerTodosXEstadoIn(true);

            if (empleados != null && !empleados.isEmpty()) {
                ResponseBase<List<EmpleadoDto>> response = new ResponseBase<>(Constans.CODIGO_OK_GET, Constans.MENSAJE_OK, Optional.of(empleados));
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ResponseBase<List<EmpleadoDto>> response = new ResponseBase<>(Constans.CODIGO_NULL_POINTER, Constans.MENSAJE_NULL_POINTER, Optional.empty());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (NullPointerException e) {
            ResponseBase<List<EmpleadoDto>> response = new ResponseBase<>(Constans.CODIGO_NULL_POINTER, Constans.MENSAJE_NULL_POINTER, Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (RuntimeException e) {
            ResponseBase<List<EmpleadoDto>> response = new ResponseBase<>(Constans.CODIGO_RUNTIME_EXCEPTION, Constans.MENSAJE_RUNTIME_EXCEPTION, Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ResponseBase<List<EmpleadoDto>> response = new ResponseBase<>(Constans.CODIGO_EXCEPTION, Constans.MENSAJE_EXCEPTION, Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponseBase<EmpleadoDto>> actualizarEmpleado(@PathVariable Long id, @RequestBody EmpleadoDto empleadoDto) {
        try {
            EmpleadoDto updatedEmpleado = empleadoServiceIn.actualizarEmpleadoIn(id, empleadoDto);
            if (updatedEmpleado != null) {
                ResponseBase<EmpleadoDto> response = new ResponseBase<>(Constans.CODIGO_OK_UPDATE, Constans.MENSAJE_OK_UPDATE, Optional.of(updatedEmpleado));
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ResponseBase<EmpleadoDto> response = new ResponseBase<>(Constans.CODIGO_EXCEPTION, Constans.MENSAJE_EXCEPTION, Optional.empty());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (NullPointerException e) {
            ResponseBase<EmpleadoDto> response = new ResponseBase<>(Constans.CODIGO_NULL_POINTER, Constans.MENSAJE_NULL_POINTER, Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            ResponseBase<EmpleadoDto> response = new ResponseBase<>(Constans.CODIGO_RUNTIME_EXCEPTION, Constans.MENSAJE_RUNTIME_EXCEPTION, Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ResponseBase<EmpleadoDto> response = new ResponseBase<>(Constans.CODIGO_EXCEPTION, Constans.MENSAJE_EXCEPTION, Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Eliminar empleado lógicamente
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarEmpleado(@PathVariable Long id) {
        try {
            empleadoServiceIn.eliminarEmpleadoLogicoIn(id);

            ResponseBase<Void> response = new ResponseBase<>(Constans.CODIGO_OK_DELETE, Constans.MENSAJE_OK_DELETE, Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

        } catch (NullPointerException e) {
            ResponseBase<Void> response = new ResponseBase<>(Constans.CODIGO_NULL_POINTER, Constans.MENSAJE_NULL_POINTER, Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        } catch (RuntimeException e) {
            ResponseBase<Void> response = new ResponseBase<>(Constans.CODIGO_RUNTIME_EXCEPTION, Constans.MENSAJE_RUNTIME_EXCEPTION, Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            ResponseBase<Void> response = new ResponseBase<>(Constans.CODIGO_EXCEPTION, Constans.MENSAJE_EXCEPTION, Optional.empty());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
