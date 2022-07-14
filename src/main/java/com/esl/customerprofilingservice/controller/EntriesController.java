package com.esl.customerprofilingservice.controller;

import com.esl.customerprofilingservice.Dto.EntriesDTO;
import com.esl.customerprofilingservice.Dto.ResponseDTO;
import com.esl.customerprofilingservice.services.EntriesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/entries")
public class EntriesController {

    private final EntriesService entriesService;

    @PostMapping
    public ResponseDTO createEntries(@RequestBody EntriesDTO entriesDto) {
        return entriesService.createEntries(entriesDto);
    }

    @GetMapping("/filter")
    public Map<String, Object> fetchEntries(@RequestParam Map<String, String> allRequestParams){
        return entriesService.fetchEntriesByFilter(allRequestParams);
    }
}
