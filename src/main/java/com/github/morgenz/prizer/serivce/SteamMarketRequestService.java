package com.github.morgenz.prizer.serivce;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.morgenz.prizer.domain.Item;
import com.github.morgenz.prizer.domain.SteamMarketItemInfo;
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

    private JsonUtils jsonUtils;

    public SteamMarketRequestService(JsonUtils jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public SteamMarketItemInfo getMarketItemInfo(Item item) throws IOException, InterruptedException {
        SteamMarketItemInfo steamMarketItemInfo = new SteamMarketItemInfo();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(getItemUri(item)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.readerForUpdating(steamMarketItemInfo);
        return objectReader.readValue(response.body());
    }


    public List<Item> getItemsFromSteamAccount(String steamAccountID, String gameId) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(getAccountUri(steamAccountID, gameId)))
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


    private String getItemUri(Item item) {
        String stringBuilder = BASIC_STEAM_MARKET_URL +
                STEAM_MARKET_HASH_NAME +
                item.getMarketHashName() +
                STEAM_MARKET_GAME_ID +
                item.getGameId() +
                STEAM_MARKET_CURRENCY_ID +
                EURO_ID;
        return stringBuilder.replace(" ", "%20").replace("|", "%7C");
    }

    private String getAccountUri(String steamAccountID, String gameId) {
        return BASIC_STEAM_INVENTORY_URL + steamAccountID + "/" + gameId + "/" + 2;
    }
}

