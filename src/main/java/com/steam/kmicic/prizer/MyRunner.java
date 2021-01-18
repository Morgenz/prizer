package com.steam.kmicic.prizer;

import com.steam.kmicic.prizer.repository.ItemRepository;
import com.steam.kmicic.prizer.repository.ListingRepository;
import com.steam.kmicic.prizer.repository.UserRepository;
import com.steam.kmicic.prizer.serivce.JsonUtils;
import com.steam.kmicic.prizer.serivce.SteamMarketItemService;
import com.steam.kmicic.prizer.serivce.SteamMarketRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ListingRepository listingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SteamMarketRequestService steamMarketRequestService;

    @Autowired
    JsonUtils jsonUtils;

    @Autowired
    SteamMarketItemService steamMarketItemService;

    @Override
    public void run(String... args) throws Exception {
//
//        List<Item> items=steamMarketRequestService.getItemsFromSteamAccount("76561198052055612","730");
//
//        items.forEach(item -> steamMarketItemService.updateMarketInfo(item));
//
//        System.out.println(items);
//        List<Item> items = new LinkedList<>();
//        Item item1 = new Item();
//        Item item2 = new Item();
//        Item item3 = new Item();
//        item1.setCurrentSteamPrice("1");
//        item1.setName("item_testowy1");
//        item1.setAmount(5);
//        item2.setCurrentSteamPrice("2");
//        item2.setName("item_testowy2");
//        item2.setAmount(1);
//        item3.setCurrentSteamPrice("3");
//        item3.setName("item_testowy3");
//        item3.setAmount(20);
//        Listing listing = new Listing();
//        listing.setItems(Arrays.asList(item1,item2,item3));
//        Listing listing2 = new Listing();
//
//
//        listing2.setItems(Arrays.asList(item1,item2));
//
//        Listing listing3 = new Listing();
//        User user = new User();
//        user.setId(1);
//        user.setUsername("user");
//        user.setPassword("password");
//        user.setItemLists(Collections.singletonList(listing3));
//        userRepository.save(user);
    }
}
