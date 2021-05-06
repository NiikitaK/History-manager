package com.springboot.history.app.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DeployHistoryDTO {
    private String user;
    private String result;
    private String deployName;
    private String platformType;
    private String platformVersion;
}
