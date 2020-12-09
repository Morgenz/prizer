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

    }
}
