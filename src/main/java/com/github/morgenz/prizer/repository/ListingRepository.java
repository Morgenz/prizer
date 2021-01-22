package com.github.morgenz.prizer.repository;

import com.github.morgenz.prizer.domain.Listing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends CrudRepository<Listing, String> {
}
