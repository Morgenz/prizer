package com.github.morgenz.prizer.controller;

import com.github.morgenz.prizer.domain.entity.Item;
import com.github.morgenz.prizer.repository.ItemRepository;
import com.github.morgenz.prizer.serivce.ItemService;
import com.github.morgenz.prizer.serivce.SteamMarketItemService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;
    private ItemRepository itemRepository;
    private SteamMarketItemService steamMarketItemService;

    public ItemController(ItemService itemService, ItemRepository itemRepository, SteamMarketItemService steamMarketItemService) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
        this.steamMarketItemService = steamMarketItemService;
    }

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
