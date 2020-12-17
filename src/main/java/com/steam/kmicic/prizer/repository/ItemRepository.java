package com.steam.kmicic.prizer.repository;

import com.steam.kmicic.prizer.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, String> {
}
