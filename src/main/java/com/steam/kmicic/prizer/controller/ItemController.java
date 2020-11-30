package com.steam.kmicic.prizer.controller;

import com.steam.kmicic.prizer.domain.Item;
import com.steam.kmicic.prizer.repository.ItemRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/all")
    public List<Item> allItems(Model model) {
        return (List<Item>) itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public Item allItems(@PathVariable String id) {
        return (Item) itemRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new NoSuchElementException("No item with given Id:" + id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json" )
    public void add(@RequestBody Item item) {
        itemRepository.save(item);
    }

}
