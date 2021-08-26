package com.sura.constructores.constructor.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Getter
@Setter
@Document(collection = "building")
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
