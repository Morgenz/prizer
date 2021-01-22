package com.github.morgenz.prizer.serivce;

import com.github.morgenz.prizer.domain.ApplicationUser;
import com.github.morgenz.prizer.domain.Item;
import com.github.morgenz.prizer.domain.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {

    @Autowired
    SteamMarketRequestService steamMarketRequestService;

    public void updateSteamListing(ApplicationUser applicationUser, Listing listing) {
        listing.setItems(steamMarketRequestService.getItemsFromSteamAccount(applicationUser.getSteamId(), "720"));
    }

    public void addSteamListing(ApplicationUser applicationUser) {
        List<Item> steamItemList = steamMarketRequestService.getItemsFromSteamAccount(applicationUser.getSteamId(), "720");
        Listing listing = new Listing();
        listing.setItems(steamItemList);
        listing.setApplicationUser(applicationUser);
        listing.setSteamInventoryList(true);
        applicationUser.getItemLists().add(listing);
    }
}
