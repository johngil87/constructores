package com.sura.constructores.constructor.entities;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "material")
@Builder
@Setter
@Getter
@Data
public class MaterialEntity {

    @Id
    private String id;
    private String type;
    private Double amount;

}
