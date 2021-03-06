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
@Document(collection = "project")
public class ProjectStatusEntity {

    @Id
    private String id;
    private Date finishDate;
    private String nameProject;
}
