package com.esl.billermanagerservice.services;

import com.esl.billermanagerservice.Dto.EntriesDTO;
import com.esl.billermanagerservice.Dto.ResponseDTO;
import com.esl.billermanagerservice.Dto.filter.EntriesFilter;
import com.esl.billermanagerservice.Utilites.Helper;
import com.esl.billermanagerservice.dao.EntriesDao;
import com.esl.billermanagerservice.model.Entries;
import com.esl.billermanagerservice.specification.EntriesSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class EntriesService {

    private final EntriesDao entriesDao;
    private final ModelMapper modelMapper;

    public ResponseDTO createEntries(EntriesDTO entriesDto) {

        Entries entries = modelMapper.map(entriesDto, Entries.class);

        return new ResponseDTO("00", "Success", entriesDao.save(entries));
    }

    public Map<String, Object> fetchEntriesByFilter(Map<String, String> allRequestParams) {
        EntriesFilter entriesFilter = new EntriesFilter();
        try {
            if (!Helper.isNullOrEmpty(allRequestParams.get("customerIdentifier"))) {
                entriesFilter.setCustomerIdentifier(allRequestParams.get("customerIdentifier"));
            }

            if (!Helper.isNullOrEmpty(allRequestParams.get("owner"))) {
                entriesFilter.setOwner(allRequestParams.get("owner"));
            }
            entriesFilter.setDraw(Integer.parseInt(allRequestParams.get("draw")));
            entriesFilter.setStart(Integer.parseInt(allRequestParams.get("start")));
            entriesFilter.setLength(Integer.parseInt(allRequestParams.get("length")));

            Pageable pageable = PageRequest.of(entriesFilter.getStartPage(), entriesFilter.getLength(), Sort.by(Sort.Direction.ASC, "id"));

            Specification<Entries> specCustomerIdentifier = EntriesSpecification.withCustomerIdentifier(entriesFilter.getCustomerIdentifier());
            Specification<Entries> specOwner = EntriesSpecification.withOwner(entriesFilter.getOwner());

            Page<Entries> result = entriesDao.findAll(
                    Specification.where(specCustomerIdentifier)
                            .and(specOwner),
                    pageable
            );

            Map<String, Object> response = new HashMap<>();

            response.put("data", result.getContent());
            response.put("draw", entriesFilter.getDraw());
            response.put("recordsTotal", result.getTotalElements());
            response.put("recordsFiltered", result.getTotalElements());

            return response;
        }catch (Exception e) {
            log.error("Exception occurred while fetching Entries {}", e.getMessage());
        }
        return Collections.emptyMap();
    }
}
