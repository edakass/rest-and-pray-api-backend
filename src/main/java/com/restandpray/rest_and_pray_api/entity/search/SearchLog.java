package com.restandpray.rest_and_pray_api.entity.search;

import com.restandpray.rest_and_pray_api.entity.user.User;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class SearchLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String query;
    private Double lat;
    private Double lng;

    private String selectedOsmId;
    private Long selectedPlaceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Instant createdAt;

    @PrePersist
    void pre() { if (createdAt == null) createdAt = Instant.now(); }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getSelectedOsmId() {
        return selectedOsmId;
    }

    public void setSelectedOsmId(String selectedOsmId) {
        this.selectedOsmId = selectedOsmId;
    }

    public Long getSelectedPlaceId() {
        return selectedPlaceId;
    }

    public void setSelectedPlaceId(Long selectedPlaceId) {
        this.selectedPlaceId = selectedPlaceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
