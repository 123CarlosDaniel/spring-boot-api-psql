package com.cdcm.apirestpsql.model.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
    private Integer status;
    private Boolean error;
    private CustomError errorData;
    private Object data;
}
