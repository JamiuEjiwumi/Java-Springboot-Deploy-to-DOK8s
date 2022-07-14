package com.esl.customerprofilingservice.dao;

import com.esl.customerprofilingservice.model.Entries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EntriesDao extends JpaRepository<Entries, Long>, JpaSpecificationExecutor<Entries> {
}
