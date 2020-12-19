package com.steam.kmicic.prizer.controller;

import com.steam.kmicic.prizer.domain.Item;
import com.steam.kmicic.prizer.repository.ItemRepository;
import com.steam.kmicic.prizer.serivce.SteamMarketItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SteamMarketItemService steamMarketItemService;

    @GetMapping("/all")
    public List<Item> allItems(Model model) {
        return (List<Item>) itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public Item findAllItems(@PathVariable Integer id) {
        return (Item) itemRepository.findById(id.toString()).orElseThrow(() -> new NoSuchElementException("No item with given Id:" + id));
    }

    @PostMapping
    public void addItem(@Valid @RequestBody Item item) {
        steamMarketItemService.updateMarketInfo(item);
        itemRepository.save(item);
    }

    @PutMapping("/{id}")
    public void updateItem(@Valid @RequestBody Item item, @PathVariable Integer id) throws NoSuchElementException, IOException, InterruptedException {
        itemRepository.findById(id.toString()).orElseThrow(() -> new NoSuchElementException("No item with given Id:" + id));
        steamMarketItemService.updateMarketInfo(item);
        itemRepository.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        itemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No item with given Id:" + id));
        itemRepository.deleteById(id);
    }
}
