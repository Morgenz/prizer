package com.steam.kmicic.prizer.serivce;

import com.steam.kmicic.prizer.domain.Item;
import com.steam.kmicic.prizer.domain.SteamMarketItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class SteamMarketItemService {

    @Autowired
    private SteamMarketRequestService steamMarketRequestService;

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
