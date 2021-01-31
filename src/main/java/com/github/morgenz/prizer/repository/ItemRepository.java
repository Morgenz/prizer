package com.github.morgenz.prizer.repository;

import com.github.morgenz.prizer.domain.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, String> {
}
