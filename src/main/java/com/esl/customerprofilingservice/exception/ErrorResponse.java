package com.esl.customerprofilingservice.exception;

import com.esl.customerprofilingservice.Dto.ResponseDTO;

public class ErrorResponse extends ResponseDTO {

    public ErrorResponse(String responseCode, String responseMessage, Object data) {
        super(responseCode, responseMessage, data);
    }

    public ErrorResponse(String responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }
}
