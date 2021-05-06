package com.springboot.history.app.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "deployHistory")
public class DeployHistory {
    @Id
    private String id;
    private Date date;
    private String user;
    private String result;
    private String deployName;
    private String platformType;
    private String platformVersion;
}
