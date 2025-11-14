package com.restandpray.rest_and_pray_api.controller.review;

import com.restandpray.rest_and_pray_api.dto.review.*;
import com.restandpray.rest_and_pray_api.entity.review.PlaceReview;
import com.restandpray.rest_and_pray_api.enums.Cleanliness;
import com.restandpray.rest_and_pray_api.enums.Religion;
import com.restandpray.rest_and_pray_api.repository.place.PlaceRepository;
import com.restandpray.rest_and_pray_api.repository.place.PlaceReviewRepository;
import com.restandpray.rest_and_pray_api.repository.user.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/places/{placeId}/reviews")
public class ReviewController {
    private final PlaceRepository placeRepo;
    private final PlaceReviewRepository reviewRepo;

    private final UserRepository userRepository;

    public ReviewController(PlaceRepository placeRepo,
                            PlaceReviewRepository reviewRepo,
                            UserRepository userRepository) {
        this.placeRepo = placeRepo;
        this.reviewRepo = reviewRepo;
        this.userRepository = userRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> create(@PathVariable Long placeId,
                                    @RequestBody ReviewCreateRequest req,
                                    Authentication auth) throws Exception {
        var username = auth.getName();
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        System.out.println("Creating review by USER = " + username + " for placeId = " + placeId);

        var place = placeRepo.findById(placeId)
                .orElseThrow(() -> new Exception("Place"));

        var review = new PlaceReview();
        review.setUser(user);
        review.setPlace(place);
        review.setRating(req.rating());
        review.setComment(req.comment());

        if (req.maleCleanliness() != null)
            review.setMaleCleanliness(Cleanliness.from(req.maleCleanliness()));
        if (req.femaleCleanliness() != null)
            review.setFemaleCleanliness(Cleanliness.from(req.femaleCleanliness()));

        review.setPaid(req.isPaid());
        review.setFeeCents(req.feeCents());
        review.setHasWudu(req.hasWudu());
        review.setHasPrayerRoom(req.hasPrayerRoom());
        if (req.prayerRoomReligion() != null)
            review.setPrayerRoomReligion(Religion.from(req.prayerRoomReligion()));

        review.setHasSockets(req.hasSockets());
        review.setHasBabyChange(req.hasBabyChange());
        review.setHasTissue(req.hasTissue());
        review.setHasStaff(req.hasStaff());
        review.setMaleToiletWestern(req.maleToiletWestern());
        review.setMaleToiletSquat(req.maleToiletSquat());
        review.setMaleUrinal(req.maleUrinal());
        review.setFemaleToiletWestern(req.femaleToiletWestern());
        review.setFemaleToiletSquat(req.femaleToiletSquat());

        reviewRepo.save(review);
        return ResponseEntity.ok().build();
    }

    @Transactional(readOnly = true)
    @GetMapping
    public Page<ReviewDto> list(@PathVariable Long placeId,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size) {
        var p = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return reviewRepo.findAllByPlaceId(placeId, p).map(ReviewDto::from);
    }
}