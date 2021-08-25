package com.sura.constructores.constructor.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrdenConstruccionDTO {

    private String idOrden;
    private String tipo;
    private String estado;
    private String fechaInicio;
    private String fechaFinalizacion;
    private Double coordenadasX;
    private Double coordenadasY;
}
