package com.sura.constructores.constructor.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Builder
@Setter
@Getter
@Document(collection = "order")
public class BuildOrderEntity {

    @Id
    private String id;
    private String type;
    private String status;
    private Date startDate;
    private Date finishDate;
    private Double coordinateX;
    private Double coordinateY;
}
