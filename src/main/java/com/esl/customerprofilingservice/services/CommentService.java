package com.esl.billermanagerservice.services;

import com.esl.billermanagerservice.Dto.CommentDTO;
import com.esl.billermanagerservice.Dto.ResponseDTO;
import com.esl.billermanagerservice.dao.CommentDao;
import com.esl.billermanagerservice.dao.EntriesDao;
import com.esl.billermanagerservice.exception.RecordNotFoundException;
import com.esl.billermanagerservice.model.Comment;
import com.esl.billermanagerservice.model.Entries;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Slf4j
@Service
@AllArgsConstructor
public class CommentService {

    private final CommentDao commentDao;
    private final EntriesDao entriesDao;

    public ResponseDTO createComment(CommentDTO commentDTO){
        Entries entries = entriesDao.findById(commentDTO.getEntryId())
                .orElseThrow(() -> new RecordNotFoundException("Entry not found"));

        Comment comment = new Comment();

        comment.setEntryId(commentDTO.getEntryId());
        comment.setOwner(commentDTO.getOwner());
        comment.setContent(commentDTO.getContent());
        comment.setEntries(entries);

        return new ResponseDTO("00", "Successful", commentDao.save(comment));
    }

}
