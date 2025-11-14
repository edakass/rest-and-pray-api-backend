package com.restandpray.rest_and_pray_api.repository.place;

import com.restandpray.rest_and_pray_api.entity.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByOsmId(String osmId);

    @Query("""
             select p from Place p
             where lower(p.name) = lower(:name)
               and abs(p.lat - :lat) < 0.0003
               and abs(p.lng - :lng) < 0.0003
            """)
    Optional<Place> findByNameAndCoords(@Param("name") String name,
                                        @Param("lat") double lat,
                                        @Param("lng") double lng);
}