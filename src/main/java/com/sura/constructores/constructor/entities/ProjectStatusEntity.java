package com.sura.constructores.constructor.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "project")
@Builder
@Setter
@Getter
public class ProjectStatusEntity {

    private String finishDate;
    private Integer totalWorks;
    private Integer finishedWorks;
    private Integer pendingWorks;
    private Integer progressWorks;
}
