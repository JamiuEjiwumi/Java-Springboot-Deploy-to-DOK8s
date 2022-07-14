package com.esl.customerprofilingservice.Dto.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EntriesFilter extends FilterRequest{
    private String customerIdentifier;
    private String owner;

}
