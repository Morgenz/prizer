package com.steam.kmicic.prizer.repository;

import com.steam.kmicic.prizer.domain.Listing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends CrudRepository<Listing, String> {
}
