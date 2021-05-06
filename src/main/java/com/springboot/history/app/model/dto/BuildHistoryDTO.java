package com.springboot.history.app.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BuildHistoryDTO {
    private String user;
    private String result;
    private String buildName;
    private String artifactType;
}
