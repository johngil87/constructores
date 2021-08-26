package com.sura.constructores.constructor.mapper;

import com.sura.constructores.constructor.DTOs.OrdenConstruccionDTO;
import com.sura.constructores.constructor.entities.BuildOrderEntity;

public class OrderMap {

    public OrdenConstruccionDTO entityToDto(BuildOrderEntity entity){
        return OrdenConstruccionDTO.builder()
                .idOrden(entity.getId())
                .tipo(entity.getType())
                .estado(entity.getStatus())
                .fechaInicio(entity.getStartDate())
                .fechaFinalizacion(entity.getFinishDate())
                .coordenadasX(entity.getXCoordinate())
                .coordenadasY(entity.getYCoordinate())
                .build();
    }

    public BuildOrderEntity dtoToEntity(OrdenConstruccionDTO dto){
        return BuildOrderEntity.builder()
                .id(dto.getIdOrden())
                .type(dto.getTipo())
                .status(dto.getEstado())
                .startDate(dto.getFechaInicio())
                .finishDate(dto.getFechaFinalizacion())
                .xCoordinate(dto.getCoordenadasX())
                .yCoordinate(dto.getCoordenadasY())
                .build();
    }
}
