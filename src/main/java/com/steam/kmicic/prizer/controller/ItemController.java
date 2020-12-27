package com.steam.kmicic.prizer.controller;

import com.steam.kmicic.prizer.domain.Item;
import com.steam.kmicic.prizer.repository.ItemRepository;
import com.steam.kmicic.prizer.serivce.ItemService;
import com.steam.kmicic.prizer.serivce.SteamMarketItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SteamMarketItemService steamMarketItemService;

    @GetMapping()
    public List<Item> allItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable String id) {
        return itemService.getItemById(id);
    }

    @PostMapping
    public void addItem(@Valid @RequestBody Item item) {
        itemService.addItem(item);
    }

    @PutMapping("/{id}")
    public void updateItem(@Valid @RequestBody Item item, @PathVariable String id) throws NoSuchElementException, IOException, InterruptedException {
        itemService.updateItemById(item, id);
    }

//    @PutMapping("/{id}")
//    public void updateSteamItemInformation(@Valid @RequestBody Item item, @PathVariable String id) throws NoSuchElementException, IOException, InterruptedException {
//        itemService.updateSteamItemInformation(item, id);
//    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
    }
}
