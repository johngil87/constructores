package com.sura.constructores.constructor.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EstadoProyectoDTO {

    private String fechaFinal;
    private Integer obrasTotales;
    private Integer obrasTerminadas;
    private Integer obrasPendientes;
    private Integer obrasProgreso;
}
