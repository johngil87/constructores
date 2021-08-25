package com.sura.constructores.constructor.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
@Builder
@Setter
@Getter
public class BuildOrderEntity {

    @Id
    private String id;
    private String type;
    private String status;
    private String startDate;
    private String finishDate;
    private Double xCoordinate;
    private Double yCoordinate;
}
