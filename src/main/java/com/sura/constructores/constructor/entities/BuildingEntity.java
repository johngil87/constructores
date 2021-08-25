package com.sura.constructores.constructor.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "building")
@Builder
@Getter
@Setter
public class BuildingEntity {

    @Id
    private String id;
    private String typeBuilding;
    private Double amountCement;
    private Double amountGravel;
    private Double amountSand;
    private Double amountWood;
    private Double amountAdobe;
    private Integer days;
}
