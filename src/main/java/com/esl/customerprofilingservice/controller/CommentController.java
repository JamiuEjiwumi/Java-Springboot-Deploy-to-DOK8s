package com.esl.customerprofilingservice.controller;

import com.esl.customerprofilingservice.Dto.CommentDTO;
import com.esl.customerprofilingservice.Dto.ResponseDTO;
import com.esl.customerprofilingservice.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;


    @PostMapping
    public ResponseDTO createComment(@Valid @RequestBody CommentDTO commentDTO) {
        return commentService.createComment(commentDTO);
    }


}
