package com.restandpray.rest_and_pray_api.entity.review;

import com.restandpray.rest_and_pray_api.entity.place.Place;
import com.restandpray.rest_and_pray_api.entity.user.User;
import jakarta.persistence.*;

import java.time.Instant;

import com.restandpray.rest_and_pray_api.enums.Cleanliness;
import com.restandpray.rest_and_pray_api.enums.Religion;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "place_reviews", indexes = {
        @Index(name = "idx_review_place", columnList = "place_id"),
        @Index(name = "idx_review_created", columnList = "createdAt")
})
public class PlaceReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    private Integer rating;
    @Column(length = 2000)
    private String comment;

    @Enumerated(EnumType.STRING)
    private Cleanliness maleCleanliness;
    @Enumerated(EnumType.STRING)
    private Cleanliness femaleCleanliness;

    private Boolean isPaid;
    private Integer feeCents;

    private Boolean hasWudu;
    private Boolean hasPrayerRoom;
    @Enumerated(EnumType.STRING)
    @Column(name = "prayer_room_religion", length = 32)
    private Religion prayerRoomReligion;

    private Boolean hasSockets;
    private Boolean hasBabyChange;
    private Boolean hasTissue;
    private Boolean hasStaff;

    private Boolean maleToiletWestern, maleToiletSquat, maleUrinal;
    private Boolean femaleToiletWestern, femaleToiletSquat;

    @PrePersist
    void pre() {
        if (createdAt == null) createdAt = Instant.now();
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Cleanliness getMaleCleanliness() {
        return maleCleanliness;
    }

    public void setMaleCleanliness(Cleanliness maleCleanliness) {
        this.maleCleanliness = maleCleanliness;
    }

    public Cleanliness getFemaleCleanliness() {
        return femaleCleanliness;
    }

    public void setFemaleCleanliness(Cleanliness femaleCleanliness) {
        this.femaleCleanliness = femaleCleanliness;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Integer getFeeCents() {
        return feeCents;
    }

    public void setFeeCents(Integer feeCents) {
        this.feeCents = feeCents;
    }

    public Boolean getHasWudu() {
        return hasWudu;
    }

    public void setHasWudu(Boolean hasWudu) {
        this.hasWudu = hasWudu;
    }

    public Boolean getHasPrayerRoom() {
        return hasPrayerRoom;
    }

    public void setHasPrayerRoom(Boolean hasPrayerRoom) {
        this.hasPrayerRoom = hasPrayerRoom;
    }

    public Religion getPrayerRoomReligion() {
        return prayerRoomReligion;
    }

    public void setPrayerRoomReligion(Religion prayerRoomReligion) {
        this.prayerRoomReligion = prayerRoomReligion;
    }

    public Boolean getHasSockets() {
        return hasSockets;
    }

    public void setHasSockets(Boolean hasSockets) {
        this.hasSockets = hasSockets;
    }

    public Boolean getHasBabyChange() {
        return hasBabyChange;
    }

    public void setHasBabyChange(Boolean hasBabyChange) {
        this.hasBabyChange = hasBabyChange;
    }

    public Boolean getHasTissue() {
        return hasTissue;
    }

    public void setHasTissue(Boolean hasTissue) {
        this.hasTissue = hasTissue;
    }

    public Boolean getHasStaff() {
        return hasStaff;
    }

    public void setHasStaff(Boolean hasStaff) {
        this.hasStaff = hasStaff;
    }

    public Boolean getMaleToiletWestern() {
        return maleToiletWestern;
    }

    public void setMaleToiletWestern(Boolean maleToiletWestern) {
        this.maleToiletWestern = maleToiletWestern;
    }

    public Boolean getMaleToiletSquat() {
        return maleToiletSquat;
    }

    public void setMaleToiletSquat(Boolean maleToiletSquat) {
        this.maleToiletSquat = maleToiletSquat;
    }

    public Boolean getMaleUrinal() {
        return maleUrinal;
    }

    public void setMaleUrinal(Boolean maleUrinal) {
        this.maleUrinal = maleUrinal;
    }

    public Boolean getFemaleToiletWestern() {
        return femaleToiletWestern;
    }

    public void setFemaleToiletWestern(Boolean femaleToiletWestern) {
        this.femaleToiletWestern = femaleToiletWestern;
    }

    public Boolean getFemaleToiletSquat() {
        return femaleToiletSquat;
    }

    public void setFemaleToiletSquat(Boolean femaleToiletSquat) {
        this.femaleToiletSquat = femaleToiletSquat;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
