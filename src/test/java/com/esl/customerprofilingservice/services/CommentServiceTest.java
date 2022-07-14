//package com.esl.customerprofilingservice.services;
//
//import com.esl.customerprofilingservice.Dto.CommentDTO;
//import com.esl.customerprofilingservice.Dto.ResponseDTO;
//import com.esl.customerprofilingservice.dao.CommentDao;
//import com.esl.customerprofilingservice.dao.EntriesDao;
//import com.esl.customerprofilingservice.model.Comment;
//import com.esl.customerprofilingservice.model.Entries;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class CommentServiceTest {
//
//    @InjectMocks
//    CommentService commentService;
//
//    @MockBean
//    CommentDao commentDao;
//
//    @MockBean
//    EntriesDao entriesDao;
//
//    Entries entries;
//    Comment comment;
//    CommentDTO commentDTO;
//
//    @BeforeEach
//    void setUp() {
//        entries = new Entries();
//        entries.setId(1L);
//        entries.setOwner("rando");
//        entries.setCustomerIdentifier("Hello World");
//        entries.setEntry("entry");
//
//        comment = new Comment();
//        comment.setId(1L);
//        comment.setEntryId(1L);
//        comment.setOwner("user");
//        comment.setContent("some random contents");
//        comment.setCreatedAt(LocalDateTime.now());
//        comment.setUpdatedAt(LocalDateTime.now());
//
//        commentDTO = new CommentDTO();
//        commentDTO.setEntryId(1L);
//        commentDTO.setOwner("user");
//        commentDTO.setContent("some random contents");
//    }
//
//    @Test
//    void createComment() {
//        Mockito.when(entriesDao.findById(Mockito.anyLong())).thenReturn(Optional.of(entries));
//        Mockito.when(commentDao.save(comment)).thenReturn(comment);
//
//        ResponseDTO responseExpected = new ResponseDTO("00", "Successful", null);
//        ResponseDTO actualResponse = commentService.createComment(commentDTO);
//
//        assertThat(responseExpected.getResponseCode()).isEqualTo(actualResponse.getResponseCode());
//    }
//}