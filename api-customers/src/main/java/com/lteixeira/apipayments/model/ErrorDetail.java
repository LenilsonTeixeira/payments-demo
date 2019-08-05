package com.lteixeira.apicustomers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetail {
    private int status;
    private String error;
    private Long timestamp;
    private String path;
}
