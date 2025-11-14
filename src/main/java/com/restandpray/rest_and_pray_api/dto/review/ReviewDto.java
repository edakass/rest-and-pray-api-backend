package com.restandpray.rest_and_pray_api.dto.review;

import com.restandpray.rest_and_pray_api.entity.review.PlaceReview;
import com.restandpray.rest_and_pray_api.enums.Cleanliness;
import com.restandpray.rest_and_pray_api.enums.Religion;

import java.time.Instant;

public record ReviewDto(
        Long id,
        Integer rating,
        String comment,
        Instant createdAt,
        Cleanliness maleCleanliness,
        Cleanliness femaleCleanliness,
        Boolean maleToiletWestern,
        Boolean maleToiletSquat,
        Boolean maleUrinal,
        Boolean femaleToiletWestern,
        Boolean femaleToiletSquat,
        Boolean isPaid,
        Integer feeCents,
        Boolean hasWudu,
        Boolean hasPrayerRoom,
        Religion prayerRoomReligion,
        Boolean hasSockets,
        Boolean hasBabyChange,
        Boolean hasTissue,
        Boolean hasStaff,
        Long userId,
        String userUsername,
        String userFullName
) {
    public static ReviewDto from(PlaceReview r) {
        var u = r.getUser();
        return new ReviewDto(
                r.getId(),
                r.getRating(),
                r.getComment(),
                r.getCreatedAt(),
                r.getMaleCleanliness(),
                r.getFemaleCleanliness(),
                r.getMaleToiletWestern(),
                r.getMaleToiletSquat(),
                r.getMaleUrinal(),
                r.getFemaleToiletWestern(),
                r.getFemaleToiletSquat(),
                r.getPaid(),
                r.getFeeCents(),
                r.getHasWudu(),
                r.getHasPrayerRoom(),
                r.getPrayerRoomReligion(),
                r.getHasSockets(),
                r.getHasBabyChange(),
                r.getHasTissue(),
                r.getHasStaff(),
                u != null ? u.getId()        : null,
                u != null ? u.getUsername()  : null,
                u != null ? u.getFullName()  : null
        );
    }
}
