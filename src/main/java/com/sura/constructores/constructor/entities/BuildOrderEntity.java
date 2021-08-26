package com.sura.constructores.constructor.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "order")
@Builder
@Setter
@Getter
public class BuildOrderEntity {

    @Id
    private String id;
    private String type;
    private String status;
    private Date startDate;
    private Date finishDate;
    private Double xCoordinate;
    private Double yCoordinate;
}
