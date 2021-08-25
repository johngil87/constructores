package com.sura.constructores.constructor.mapper;

import com.sura.constructores.constructor.DTOs.MaterialDTO;
import com.sura.constructores.constructor.entities.MaterialEntity;

public class MaterialMap {

    public MaterialDTO entityToDTO(MaterialEntity entity){
        return  MaterialDTO.builder()
                .idMaterial(entity.getId())
                .tipoMaterial(entity.getType())
                .CantidadMaterial(entity.getAmount())
                .build();
    }

    public MaterialEntity dtoToEntity(MaterialDTO dto){
        return MaterialEntity.builder()
                .id(dto.getIdMaterial())
                .type(dto.getTipoMaterial())
                .amount(dto.getCantidadMaterial())
                .build();
    }
}
