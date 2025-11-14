package com.restandpray.rest_and_pray_api.dto.review;

public record ReviewCreateRequest(
        Long placeId,
        Integer rating, String comment,
        String maleCleanliness, String femaleCleanliness,
        Boolean isPaid, Integer feeCents,
        Boolean hasWudu, Boolean hasPrayerRoom, String prayerRoomReligion,
        Boolean hasSockets, Boolean hasBabyChange, Boolean hasTissue, Boolean hasStaff,
        Boolean maleToiletWestern, Boolean maleToiletSquat, Boolean maleUrinal,
        Boolean femaleToiletWestern, Boolean femaleToiletSquat
) {
}