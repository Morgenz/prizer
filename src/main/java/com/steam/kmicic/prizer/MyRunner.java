package com.steam.kmicic.prizer;

import com.steam.kmicic.prizer.domain.Item;
import com.steam.kmicic.prizer.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
        Item item1 = new Item();
        item1.setName("bron1");
        item1.setPurchasePrice(new BigDecimal("1.33"));
        item1.setCurrentSteamPrice(new BigDecimal("1.34"));
        item1.setQuantity(1);

        Item item2 = new Item();
        item2.setName("bron2");
        item2.setPurchasePrice(new BigDecimal("2.33"));
        item2.setCurrentSteamPrice(new BigDecimal("2.34"));
        item2.setQuantity(2);

        itemRepository.save(item1);
        itemRepository.save(item2);
    }
}
