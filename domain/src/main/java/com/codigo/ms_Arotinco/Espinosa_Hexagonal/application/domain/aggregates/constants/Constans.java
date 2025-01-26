package com.codigo.ms_Arotinco.Espinosa_Hexagonal.application.domain.aggregates.constants;

public class Constans {

    public static final String LOG_INICIO = "Se inicia la ejecución del Metodo: ";
    public static final String LOG_FIN = "Se Finaliza la ejecución del Metodo: ";
    public static final String LOG_ERROR = "Se ha producido un error la ejecución del Metodo: ";


    public static final boolean ESTADO_INACTIVO = false;

    public static final String BEARER = "Bearer ";
    public static final String BASE_URL = "https://api.apis.net.pe";

    public static final int CODIGO_OK_GET = 200;
    public static final int CODIGO_OK_POST = 201;
    public static final int CODIGO_OK_DELETE = 204;
    public static final String MENSAJE_OK_DELETE = "Empleado Eliminado(Logico) Correctamente";
    public static final int CODIGO_OK_UPDATE = 200;
    public static final String MENSAJE_OK_UPDATE = "Empleado Actualizado Correctamente";

    public static final String MENSAJE_OK = "Todo OK!";


    public static final Integer CODIGO_ERROR = 2004;
    public static final String MENSAJE_ERROR = "ERROR REGISTRANDO AL EMPLEADO";




    // Excepciones




    public static final int CODIGO_EXCEPTION = 500;
    public static final String MENSAJE_EXCEPTION = "Error interno del servidor";

    public static final int CODIGO_NULL_POINTER = 409;
    public static final String MENSAJE_NULL_POINTER = "Referencia nula encontrada";

    public static final int CODIGO_IO_EXCEPTION = 406;
    public static final String MENSAJE_IO_EXCEPTION = "Error en la entrada/salida";

    public static final int CODIGO_RUNTIME_EXCEPTION = 400;
    public static final String MENSAJE_RUNTIME_EXCEPTION = "Error en tiempo de ejecución";

    public static final int CODIGO_EMPLEADO_EXISTE = 409;
    public static final String MENSAJE_EMPLEADO_EXISTE = "El empleado Existe";

}
