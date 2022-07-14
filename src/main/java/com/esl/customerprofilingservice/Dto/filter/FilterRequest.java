package com.esl.customerprofilingservice.Dto.filter;

import lombok.Data;

@Data
public class FilterRequest {
    private int draw;
    private int start;
    private int length;
    private Integer startPage;

    public int getStartPage() {
        if(null != startPage){
            return startPage;
        }
        return this.start/this.length;
    }
}
