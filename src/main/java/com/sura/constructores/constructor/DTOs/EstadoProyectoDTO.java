package com.sura.constructores.constructor.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EstadoProyectoDTO {

    private String fechaFinal;
    private String nombreProyecto;
    private Integer obrasTotales;
}
