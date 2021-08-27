package com.sura.constructores.constructor.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDTO extends RespuestaDTO{

    private String obrasPendientes;
    private String obrasTerminadas;
    private String obrasEnProgreso;

    public ReportDTO(String nombreProyecto, String fecha, String respuesta, String obrasPendientes, String obrasTerminadas, String obrasEnProgreso) {
        super(nombreProyecto, fecha, respuesta);
        this.obrasPendientes = obrasPendientes;
        this.obrasTerminadas = obrasTerminadas;
        this.obrasEnProgreso = obrasEnProgreso;
    }
}
