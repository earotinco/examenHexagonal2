package com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.response;

import lombok.*;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBase<T>  {

    private int code;
    private String message;
    private Optional<T> data;


    public ResponseBase(int cod, String message) {
        this.code=cod;
        this.message=message;

    }
}
