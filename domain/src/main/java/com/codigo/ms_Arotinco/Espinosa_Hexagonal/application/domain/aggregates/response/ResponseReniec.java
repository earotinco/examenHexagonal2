package com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseReniec {

    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoDocumento;
    private String numeroDocumento;
    private String digitoVerificador;


}
