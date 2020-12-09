package com.steam.kmicic.prizer.serivce;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.steam.kmicic.prizer.domain.Item;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SteamMarketRequestService {

    public static final String BASIC_STEAM_MARKET_URL = "https://steamcommunity.com/market/priceoverview/";
    public static final String BASIC_STEAM_INVENTORY_URL = "http://steamcommunity.com/inventory/";
    public static final String STEAM_MARKET_HASH_NAME = "?market_hash_name=";
    public static final String STEAM_MARKET_GAME_ID = "&appid=";
    public static final String STEAM_MARKET_CURRENCY_ID = "&currency";
    public static final String STEAM_MARKET_CONTEXT_ID = "2";
    public static final Integer EURO_ID = 3;
    private final HttpClient client = HttpClient.newBuilder().build();


    public Item getMarketItemInfo(Item item) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(getItemURI(item)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.readerForUpdating(item);
        return objectReader.readValue(response.body());
    }

    public void getItemsFromSteamAccount(String steamAccountID, String gameId) throws IOException, InterruptedException {
        Set<Item> itemSet = new HashSet<>();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(getAccountURI(steamAccountID, gameId)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        response.body();
    }

    private String getItemURI(Item item) {
        StringBuilder stringBuilder = new StringBuilder(BASIC_STEAM_MARKET_URL);
        stringBuilder.append(STEAM_MARKET_HASH_NAME)
                .append(item.getMarketHashName())
                .append(STEAM_MARKET_GAME_ID)
                .append(item.getGameId())
                .append(STEAM_MARKET_CURRENCY_ID)
                .append(EURO_ID);
        return stringBuilder.toString();
    }

    private String getAccountURI(String steamAccountID, String gameId) {
        StringBuilder stringBuilder = new StringBuilder(BASIC_STEAM_INVENTORY_URL);
        stringBuilder
                .append("/")
                .append(steamAccountID)
                .append("/")
                .append(gameId)
                .append("/")
                .append(2);
        return stringBuilder.toString();
    }
}

