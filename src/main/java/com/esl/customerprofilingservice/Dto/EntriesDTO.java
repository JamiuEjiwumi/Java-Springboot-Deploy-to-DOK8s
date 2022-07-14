package com.esl.customerprofilingservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntriesDTO {

    @NotEmpty(message = "owner field should not be empty")
    private String owner;

    @NotEmpty(message = "customerIdentifier field should not be empty")
    private String customerIdentifier;

    @NotEmpty(message = "entry field should not be empty")
    private String entry;

}
