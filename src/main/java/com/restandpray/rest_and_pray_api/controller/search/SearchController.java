package com.restandpray.rest_and_pray_api.controller.search;

import com.restandpray.rest_and_pray_api.dto.search.SearchLogReq;
import com.restandpray.rest_and_pray_api.entity.search.SearchLog;
import com.restandpray.rest_and_pray_api.repository.search.SearchLogRepository;
import com.restandpray.rest_and_pray_api.repository.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SearchController {
    private final SearchLogRepository searchLogRepository;
    private final UserRepository userRepository;

    public SearchController(SearchLogRepository searchLogRepository,
                            UserRepository userRepository) {
        this.searchLogRepository = searchLogRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/searches")
    public ResponseEntity<Void> searchLog(
            @RequestBody SearchLogReq req,
            @AuthenticationPrincipal org.springframework.security.core.userdetails.User principal
    ) {
        SearchLog s = new SearchLog();
        s.setQuery(req.query());
        s.setLat(req.lat());
        s.setLng(req.lng());
        s.setSelectedOsmId(req.selectedOsmId());
        s.setSelectedPlaceId(req.selectedPlaceId());

        if (principal != null) {
            userRepository.findByUsername(principal.getUsername())
                    .ifPresent(s::setUser);
        }

        searchLogRepository.save(s);
        return ResponseEntity.ok().build();
    }
}