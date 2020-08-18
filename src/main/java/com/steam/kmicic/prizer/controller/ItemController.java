package com.steam.kmicic.prizer.controller;

import com.steam.kmicic.prizer.model.Item;
import com.steam.kmicic.prizer.model.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/item")
    public String allItems(Model model){
        List<Item> items = (List<Item>) itemRepository.findAll();
        model.addAttribute("items",items);
        return "item";
    }


}
