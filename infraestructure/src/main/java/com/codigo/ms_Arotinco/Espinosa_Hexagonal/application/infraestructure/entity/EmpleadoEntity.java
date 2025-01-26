package com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Table(name="Empleado")
@Getter
@Setter
public class EmpleadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombre;
    private String apellido;
    private int edad;
    private String cargo;
    private String tipoDoc;

    @Column(unique = true)
    private String numDoc;
    private String departamento;
    private double salario;
    private String telefono;
    private String correo;
    private boolean estado;
    private String direccion;
    private Timestamp dateCrea;
    private String usuaCrea;
    private Timestamp dateUdpate;
    private String usuaUpdate;
    private Timestamp dateDelete;
    private String usuaDelete;

}
