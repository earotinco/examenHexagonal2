package com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.infraestructure.adapter;

import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.constants.Constans;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.dto.EmpleadoDto;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.response.ResponseBase;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.response.ResponseReniec;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.ports.out.EmpleadoServiceOut;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.infraestructure.entity.EmpleadoEntity;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.infraestructure.mapper.EmpleadoMapper;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.infraestructure.respository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Log4j2
public class EmpleadoOutAdapter implements EmpleadoServiceOut {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    private final RestTemplate restTemplate;


    @Value("${token.api}")
    private String token;


    @Override
    public ResponseBase<EmpleadoDto> crearEmpleadoOut(EmpleadoDto empleadoDto) {
        ResponseBase<EmpleadoDto> response = new ResponseBase<>();

        log.info("EmpleadoOUTADAPTER DNI: "+empleadoDto.getNumDoc());



        if (empleadoRepository.existsByNumDoc(empleadoDto.getNumDoc())){
            log.info("Empleado EXISTSBY ID: "+empleadoDto.getNumDoc());
            response.setCode(Constans.CODIGO_EMPLEADO_EXISTE);
            response.setMessage(Constans.MENSAJE_EMPLEADO_EXISTE);
            response.setData(Optional.empty());
        }else {

        ResponseReniec responseReniec = executeRestTemplate(empleadoDto.getNumDoc());
        EmpleadoEntity empleadoEntity = new EmpleadoEntity();
        log.info("Respuesta completa: {}", responseReniec);

        empleadoEntity.setNombre(responseReniec.getNombres());
        empleadoEntity.setApellido(responseReniec.getApellidoPaterno() + " " +
                responseReniec.getApellidoMaterno());
        empleadoEntity.setTipoDoc(responseReniec.getTipoDocumento());
        empleadoEntity.setNumDoc(responseReniec.getNumeroDocumento());
        empleadoEntity.setEdad(empleadoDto.getEdad());
        empleadoEntity.setCargo(empleadoDto.getCargo());
        empleadoEntity.setNumDoc(empleadoDto.getNumDoc());
        empleadoEntity.setDepartamento(empleadoDto.getDepartamento());
        empleadoEntity.setSalario(empleadoDto.getSalario());
        empleadoEntity.setTelefono(empleadoDto.getTelefono());
        empleadoEntity.setCorreo(empleadoDto.getCorreo());

        empleadoEntity.setEstado(true);

        empleadoEntity.setDireccion(empleadoDto.getDireccion());
        empleadoEntity.setUsuaCrea("Earotinco");
        empleadoEntity.setDateCrea(new Timestamp(System.currentTimeMillis()));


        empleadoEntity = empleadoRepository.save(empleadoEntity);



            response.setCode(Constans.CODIGO_OK_POST);
            response.setMessage(Constans.MENSAJE_OK);
            response.setData(Optional.of(empleadoMapper.mapToDto(empleadoEntity)));
        }
        return response;
    }

    @Override
    public EmpleadoDto buscarEmpleadoOut(Long id) {
        Optional<EmpleadoEntity> empleadoEntityOptional = empleadoRepository.findById(id);

        if (empleadoEntityOptional.isPresent()) {
            return empleadoMapper.mapToDto(empleadoEntityOptional.get());
        } else {
            return null; // lanzar una excepción
        }
    }

    @Override
    public EmpleadoDto buscarEmpleadoDniOut(String dni) {
        Optional<EmpleadoEntity> empleadoEntityOptional = empleadoRepository.findByNumDoc(dni);

        if (empleadoEntityOptional.isPresent()) {
            return empleadoMapper.mapToDto(empleadoEntityOptional.get());
        } else {
            return null; // lanzar una excepción
        }
    }

    @Override
    public List<EmpleadoDto> obtenerTodosXEstadoOut(boolean estado) {
        List<EmpleadoEntity> empleadosEntityList = empleadoRepository.buscarEstudiantesXEstado(estado);
        return empleadoMapper.mapToDtoList(empleadosEntityList);
    }

    @Override
    public EmpleadoDto actualizarEmpleadoOut(Long id, EmpleadoDto empleadoDto) {
        //Recupero al registro de BD
        EmpleadoEntity empleadoExistente = empleadoMapper.mapToEntity(buscarEmpleadoOut(id));


        empleadoExistente.setEdad(empleadoDto.getEdad());
        empleadoExistente.setCargo(empleadoDto.getCargo());
        empleadoExistente.setSalario(empleadoDto.getSalario());
        empleadoExistente.setTelefono(empleadoDto.getTelefono());
        empleadoExistente.setCorreo(empleadoDto.getCorreo());
        empleadoExistente.setDepartamento(empleadoDto.getDepartamento());
        empleadoExistente.setDireccion(empleadoDto.getDireccion());
        empleadoExistente.setDateUdpate(new Timestamp(System.currentTimeMillis()));
        empleadoExistente.setUsuaUpdate("Earotinco-Update");

        empleadoExistente = empleadoRepository.save(empleadoExistente);



        return empleadoMapper.mapToDto(empleadoExistente);


    }

    @Override
    public void eliminarEmpleadoLogicoOut(Long id) {

        //Identificar el registro
        EmpleadoEntity empleadoExistente = empleadoMapper.mapToEntity(buscarEmpleadoOut(id));
        empleadoExistente.setEstado(Constans.ESTADO_INACTIVO);
        empleadoExistente.setDateDelete(new Timestamp(System.currentTimeMillis()));
        empleadoExistente.setUsuaDelete("Earotinco-Delete");
        //eliminando
        empleadoRepository.save(empleadoExistente);
    }


    ///////////////////////////////////////////

    private ResponseReniec executeRestTemplate(String dni){
        //urlComplete = https://api.apis.net.pe/v2/reniec/dni?numero=74653594
        String urlComplete = Constans.BASE_URL + "/v2/reniec/dni?numero=" + dni;

        try {
            ResponseEntity<ResponseReniec> response = restTemplate.exchange(
                    urlComplete,
                    HttpMethod.GET,
                    new HttpEntity<>(createHeaders()),
                    ResponseReniec.class
            );

            //validarmos la respuesta del servicio
            if(response.getStatusCode() == HttpStatus.OK){
                return response.getBody();
            } else {
                log.warn("Respuesta inesperada del servicio externo: {}", response.getStatusCode());
            }
        } catch (Exception e){
            log.error("Error al consultar el servicio externo para el DNI: {}", dni, e);
        }

        return  null;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", Constans.BEARER+token);
        return headers;
    }
}
