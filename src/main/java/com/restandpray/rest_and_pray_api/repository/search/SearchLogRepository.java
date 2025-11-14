package com.restandpray.rest_and_pray_api.repository.search;

import com.restandpray.rest_and_pray_api.entity.search.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchLogRepository extends JpaRepository<SearchLog, Long> {
}