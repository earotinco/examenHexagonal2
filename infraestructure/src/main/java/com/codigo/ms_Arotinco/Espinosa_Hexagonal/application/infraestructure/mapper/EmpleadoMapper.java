package com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.infraestructure.mapper;


import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.dto.EmpleadoDto;
import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.infraestructure.entity.EmpleadoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    //enviamos la entidad y mapeamos hacia dto
    public EmpleadoDto mapToDto(EmpleadoEntity empleadoEntity){
        return MODEL_MAPPER.map(empleadoEntity, EmpleadoDto.class);
    }

    //enviamos el DTO y retornamos una EntityClass
    public EmpleadoEntity mapToEntity(EmpleadoDto empleadoDto){
        return MODEL_MAPPER.map(empleadoDto, EmpleadoEntity.class);
    }


    public List<EmpleadoDto> mapToDtoList(List<EmpleadoEntity> empleadosEntityList) {
        return empleadosEntityList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
