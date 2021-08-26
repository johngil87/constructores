package com.sura.constructores.constructor.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class EstadoProyectoDTO {

    private Date fechaFinal;
    private String nombreProyecto;
    private Integer obrasTotales;
}
