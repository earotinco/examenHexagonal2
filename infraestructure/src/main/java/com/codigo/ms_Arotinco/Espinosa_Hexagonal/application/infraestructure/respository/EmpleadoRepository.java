package com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.infraestructure.respository;

import com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.infraestructure.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {

    @Query("SELECT E FROM EmpleadoEntity E WHERE E.numDoc =:dni")
    Optional<EmpleadoEntity> findByNumDoc(@Param("dni")String numDoc);

    @Query("SELECT E FROM EmpleadoEntity E WHERE E.estado =:datoEstado")
    List<EmpleadoEntity> buscarEstudiantesXEstado(@Param("datoEstado") boolean datoEstado);

    boolean existsByNumDoc(String numDoc);



}
