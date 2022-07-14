package com.esl.customerprofilingservice.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentDTO {
    private Long entryId;

    @NotEmpty(message = "owner field should not be empty")
    private String owner;

    @NotEmpty(message = "content cannot be empty")
    private String content;
}
