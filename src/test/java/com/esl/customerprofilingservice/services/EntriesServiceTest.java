//package com.esl.customerprofilingservice.services;
//
//import com.esl.customerprofilingservice.Dto.EntriesDTO;
//import com.esl.customerprofilingservice.Dto.ResponseDTO;
//import com.esl.customerprofilingservice.Dto.filter.EntriesFilter;
//import com.esl.customerprofilingservice.dao.EntriesDao;
//import com.esl.customerprofilingservice.model.Comment;
//import com.esl.customerprofilingservice.model.Entries;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class EntriesServiceTest {
//
//    @Mock
//    private EntriesDao entriesDao;
//
//
//    @InjectMocks
//    private EntriesService entriesService;
//
//
//    EntriesDTO entriesDTO;
//
//    Entries entries;
//
//
//    @BeforeEach
//    void setUp() {
//
//
//       entriesDTO = new EntriesDTO();
//       entriesDTO.setOwner("TheOwner");
//       entriesDTO.setCustomerIdentifier("2");
//       entriesDTO.setEntry("Good customer");
//
//       entries = new Entries();
//       entries.setId(1L);
//       entries.setCustomerIdentifier("2");
//       entries.setEntry("Good customer");
//       entries.setOwner("TheOwner");
//       entries.setCreatedAt(LocalDateTime.now());
//       entries.setUpdatedAt(LocalDateTime.now());
//
//
//    }
//
//    @Test
//    void createEntries(){
//
//        ResponseDTO actualResponse = entriesService.createEntries(entriesDTO);
//        Mockito.when(entriesDao.findById(Mockito.anyLong())).thenReturn(Optional.of(entries));
//        Mockito.when(entriesDao.save(entries)).thenReturn(entries);
//
//        ResponseDTO responseExpected = new ResponseDTO("00", "Successful", entries);
//        assertThat(responseExpected.getResponseCode()).isEqualTo(actualResponse.getResponseCode());
//    }
//
//    @Test
//    void fetchEntries() {
//        Map<String, String> allRequestParams = buildAllRequestParams();
//        EntriesFilter entriesFilter = buildEntriesFilter(allRequestParams);
//        PageRequest pageable = PageRequest.of(entriesFilter.getStartPage(),entriesFilter.getLength(), Sort.by(Sort.Direction.ASC, "id"));
//        Entries entriesItem = buildEntries();
//        List<Entries> entriesList = new ArrayList<>();
//        entriesList.add(entriesItem);
//        Page<Entries> entriesPage = new PageImpl<>(entriesList);
//        Mockito.when(entriesDao.findAll(pageable)).thenReturn(entriesPage);
//        Map<String, Object> response = entriesService.fetchEntriesByFilter(allRequestParams);
//        System.out.println(response);
//        assertThat(response).isNotNull();
//
//
//    }
//
//    EntriesFilter buildEntriesFilter(Map<String, String> allRequestParams) {
//        EntriesFilter entriesFilter = new EntriesFilter();
//        entriesFilter.setCustomerIdentifier(allRequestParams.get("customerIdentifier"));
//        entriesFilter.setOwner(allRequestParams.get("owner"));
//        entriesFilter.setDraw(Integer.parseInt(allRequestParams.get("draw")));
//        entriesFilter.setStart(Integer.parseInt(allRequestParams.get("start")));
//        entriesFilter.setLength(Integer.parseInt(allRequestParams.get("length")));
//        return  entriesFilter;
//    }
//
//    Map<String, String> buildAllRequestParams () {
//        Map<String, String> allRequestParams = new HashMap<>();
//        allRequestParams.put("customerIdentifier", "40");
//        allRequestParams.put("owner", "John Doe");
//        allRequestParams.put("draw", "5");
//        allRequestParams.put("start", "1");
//        allRequestParams.put("length", "5");
//        return allRequestParams;
//    }
//
//
//
//    Entries buildEntries () {
//        Entries entries = new Entries();
//        List<Comment> commentList = new ArrayList<>();
//        entries.setComments(commentList);
//        entries.setCustomerIdentifier("40");
//        entries.setEntry("Reading Java");
//        entries.setId(1L);
//        entries.setOwner("John Sparrow");
//        entries.setCreatedAt(LocalDateTime.now());
//        entries.setUpdatedAt(LocalDateTime.now());
//        return entries;
//    }
//
//
//    Comment buildComment () {
//        Comment comment = new Comment();
//        comment.setOwner("John Doe");
//        comment.setEntryId(1L);
//        comment.setContent("Back to coding");
//        comment.setCreatedAt(LocalDateTime.now());
//        comment.setUpdatedAt(LocalDateTime.now());
//        return comment;
//    }
//
//}