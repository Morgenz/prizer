package com.github.morgenz.prizer.serivce;

import com.github.morgenz.prizer.domain.entity.Item;
import com.github.morgenz.prizer.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    private SteamMarketItemService steamMarketItemService;

    public ItemService(ItemRepository itemRepository, SteamMarketItemService steamMarketItemService) {
        this.itemRepository = itemRepository;
        this.steamMarketItemService = steamMarketItemService;
    }

    public Item addItem(Item item) {
       return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return (List<Item>) itemRepository.findAll();
    }

    public Item getItemById(String id) {
        return itemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No item with given Id:" + id));
    }

    public Item updateItemById(Item item, String id) {
        itemRepository.findById(id.toString()).orElseThrow(() -> new NoSuchElementException("No item with given Id:" + id));
        return itemRepository.save(item);
    }

    public Item updateSteamItemInformation(Item item, String id) {
        itemRepository.findById(id.toString()).orElseThrow(() -> new NoSuchElementException("No item with given Id:" + id));
        steamMarketItemService.updateMarketInfo(item);
        return itemRepository.save(item);
    }

    public void deleteItem(String id) {
        itemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No item with given Id:" + id));
        itemRepository.deleteById(id);
    }

}
