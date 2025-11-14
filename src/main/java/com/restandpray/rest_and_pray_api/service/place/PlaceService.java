package com.restandpray.rest_and_pray_api.service.place;


import com.restandpray.rest_and_pray_api.dto.place.PlaceCreateRequest;
import com.restandpray.rest_and_pray_api.dto.place.PlaceResponse;
import com.restandpray.rest_and_pray_api.entity.place.Place;
import com.restandpray.rest_and_pray_api.repository.place.PlaceRepository;
import com.restandpray.rest_and_pray_api.repository.place.PlaceReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {
    private final PlaceRepository places;
    private final PlaceReviewRepository reviews;

    public PlaceService(PlaceRepository places, PlaceReviewRepository reviews) {
        this.places = places;
        this.reviews = reviews;
    }

   @Transactional
    public Place getOrCreate(PlaceCreateRequest r) {
        if (r == null) throw new IllegalArgumentException("Request is null");
        return places.findByOsmId(r.getOsmId())
                .or(() -> places.findByNameAndCoords(r.getName(), r.getLat(), r.getLng()))
                .orElseGet(() -> {
                    Place p = new Place();
                    p.setName(r.getName());
                    p.setAddress(r.getAddress());
                    p.setLat(r.getLat());
                    p.setLng(r.getLng());
                    p.setOsmId(r.getOsmId());
                    return places.save(p);
                });
    }

    public Place getByIdOrThrow(Long id) {
        return places.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Place not found: " + id));
    }

    public PlaceResponse toResponse(Place p) {
        PlaceResponse r = new PlaceResponse();
        r.setId(p.getId());
        r.setName(p.getName());
        r.setAddress(p.getAddress());
        r.setLat(p.getLat());
        r.setLng(p.getLng());

        Double avg = reviews.avgRatingOfPlace(p.getId());
        Long cnt = reviews.reviewCountOfPlace(p.getId());
        r.setAverageRating(avg != null ? avg : 0.0);
        r.setReviewCount(cnt != null ? cnt : 0L);

        return r;
    }
}