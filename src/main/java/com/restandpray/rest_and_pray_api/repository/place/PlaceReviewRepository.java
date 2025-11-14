package com.restandpray.rest_and_pray_api.repository.place;

import com.restandpray.rest_and_pray_api.entity.review.PlaceReview;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Page;

public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Long> {

    @Query("select coalesce(avg(r.rating), 0) from PlaceReview r where r.place.id = :placeId")
    Double avgRatingOfPlace(@Param("placeId") Long placeId);

    @Query("select count(r) from PlaceReview r where r.place.id = :placeId")
    Long reviewCountOfPlace(@Param("placeId") Long placeId);

    @EntityGraph(attributePaths = "user")
    Page<PlaceReview> findAllByPlaceId(Long placeId, Pageable pageable);
}


