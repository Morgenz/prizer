package com.github.morgenz.prizer.serivce;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.morgenz.prizer.domain.Item;
import com.github.morgenz.prizer.domain.SteamMarketItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class SteamMarketRequestService {

    public static final String BASIC_STEAM_MARKET_URL = "https://steamcommunity.com/market/priceoverview/";
    public static final String BASIC_STEAM_INVENTORY_URL = "https://steamcommunity.com/inventory/";
    public static final String STEAM_MARKET_HASH_NAME = "?market_hash_name=";
    public static final String STEAM_MARKET_GAME_ID = "&appid=";
    public static final String STEAM_MARKET_CURRENCY_ID = "&currency";
    public static final Integer EURO_ID = 3;
    private final HttpClient client = HttpClient.newBuilder().build();

    @Autowired
    private JsonUtils jsonUtils;

    public SteamMarketItemInfo getMarketItemInfo(Item item) throws IOException, InterruptedException {
        SteamMarketItemInfo steamMarketItemInfo = new SteamMarketItemInfo();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(getItemURI(item)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.readerForUpdating(steamMarketItemInfo);
        return objectReader.readValue(response.body());
    }


    public List<Item> getItemsFromSteamAccount(String steamAccountID, String gameId) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(getAccountURI(steamAccountID, gameId)))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return jsonUtils.parseSteamInventory(response.body());
    }


    private String getItemURI(Item item) {
        StringBuilder stringBuilder = new StringBuilder(BASIC_STEAM_MARKET_URL);
        stringBuilder.append(STEAM_MARKET_HASH_NAME)
                .append(item.getMarketHashName())
                .append(STEAM_MARKET_GAME_ID)
                .append(item.getGameId())
                .append(STEAM_MARKET_CURRENCY_ID)
                .append(EURO_ID);
        return stringBuilder.toString().replace(" ", "%20").replace("|", "%7C");
    }

    private String getAccountURI(String steamAccountID, String gameId) {
        StringBuilder stringBuilder = new StringBuilder(BASIC_STEAM_INVENTORY_URL);
        stringBuilder
                .append(steamAccountID)
                .append("/")
                .append(gameId)
                .append("/")
                .append(2);
        return stringBuilder.toString();
    }
}

