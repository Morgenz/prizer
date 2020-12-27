package com.steam.kmicic.prizer.serivce;

import com.steam.kmicic.prizer.domain.Item;
import com.steam.kmicic.prizer.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    SteamMarketItemService steamMarketItemService;

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return (List<Item>) itemRepository.findAll();
    }

    public Item getItemById(String id) {
        return itemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No item with given Id:" + id));
    }

    public void updateItemById(Item item, String id) {
        itemRepository.findById(id.toString()).orElseThrow(() -> new NoSuchElementException("No item with given Id:" + id));
        itemRepository.save(item);
    }

    public void updateSteamItemInformation(Item item, String id) {
        itemRepository.findById(id.toString()).orElseThrow(() -> new NoSuchElementException("No item with given Id:" + id));
        steamMarketItemService.updateMarketInfo(item);
        itemRepository.save(item);
    }

    public void deleteItem(String id) {
        itemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No item with given Id:" + id));
        itemRepository.deleteById(id);
    }

}
