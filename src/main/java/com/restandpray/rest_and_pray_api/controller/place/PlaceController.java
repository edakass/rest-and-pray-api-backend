package com.restandpray.rest_and_pray_api.controller.place;

import com.restandpray.rest_and_pray_api.dto.place.PlaceCreateRequest;
import com.restandpray.rest_and_pray_api.dto.place.PlaceResponse;
import com.restandpray.rest_and_pray_api.entity.place.Place;
import com.restandpray.rest_and_pray_api.service.place.PlaceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private static final Logger log = LoggerFactory.getLogger(PlaceController.class);
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/import")
    public ResponseEntity<PlaceResponse> createOrGet(@RequestBody @Valid PlaceCreateRequest req) {
        Place place = placeService.getOrCreate(req);

        log.info("Place resolved with id: {} (existing: {})",
                place.getId(), place.getOsmId() != null);

        return ResponseEntity.ok(placeService.toResponse(place));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceResponse> getById(@PathVariable Long id) {
        log.info("Fetching place with id: {}", id);

        Place p = placeService.getByIdOrThrow(id);

        log.info("Place found: id: {}, name: {}", p.getId(), p.getName());

        return ResponseEntity.ok(placeService.toResponse(p));
    }
}