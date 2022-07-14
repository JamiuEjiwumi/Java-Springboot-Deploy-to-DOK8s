package com.esl.customerprofilingservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private String responseCode;
    private String responseMessage;
    private Object data;


    public ResponseDTO(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = null;
    }

}
