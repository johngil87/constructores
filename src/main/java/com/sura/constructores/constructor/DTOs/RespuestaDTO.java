package com.sura.constructores.constructor.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RespuestaDTO {
    private String nombreProyecto;
    private String fecha;
    private String respuesta;
}
