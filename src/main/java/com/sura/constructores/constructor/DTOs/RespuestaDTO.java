package com.sura.constructores.constructor.DTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RespuestaDTO {
    private String nombreProyecto;
    private String fecha;
    private String respuesta;

    public RespuestaDTO( String fecha, String respuesta) {
        this.fecha = fecha;
        this.respuesta = respuesta;
    }

    public RespuestaDTO() {
    }
}
