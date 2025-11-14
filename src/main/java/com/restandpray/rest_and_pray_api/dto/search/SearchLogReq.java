package com.restandpray.rest_and_pray_api.dto.search;

public record SearchLogReq(
        String query, Double lat, Double lng,
        String selectedOsmId, Long selectedPlaceId
) {
}