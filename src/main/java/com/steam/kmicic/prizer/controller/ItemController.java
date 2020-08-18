package com.steam.kmicic.prizer.controller;

import com.steam.kmicic.prizer.model.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ItemController {

    @Autowired
    ItemRepository itemRepository;
}
