package com.github.morgenz.prizer.serivce;

import com.github.morgenz.prizer.domain.entity.Item;
import com.github.morgenz.prizer.domain.entity.SteamMarketItemInfo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class SteamMarketItemService {

    private SteamMarketRequestService steamMarketRequestService;

    public SteamMarketItemService(SteamMarketRequestService steamMarketRequestService) {
        this.steamMarketRequestService = steamMarketRequestService;
    }

    public void updateMarketInfo(Item item) {
        try {
            SteamMarketItemInfo steamMarketItemInfo = steamMarketRequestService.getMarketItemInfo(item);
            item.setCurrencySymbol(steamMarketItemInfo.getCurrentPrice().charAt(0));
            item.setCurrentSteamPrice(BigDecimal.valueOf(Double.parseDouble(steamMarketItemInfo.getCurrentPrice().substring(1))));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
