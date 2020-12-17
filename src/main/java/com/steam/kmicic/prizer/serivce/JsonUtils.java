package com.steam.kmicic.prizer.serivce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.steam.kmicic.prizer.domain.Item;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class JsonUtils {

    ObjectMapper mapper = new ObjectMapper();

    public List<Item> mapSteamInventory(String inventory) throws JsonProcessingException {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode rootNode = mapper.readTree(inventory);
        JsonNode firstResult = rootNode.get("assets");
        Map<Integer, Item> itemMap = new HashMap<>();
        for (JsonNode node : firstResult) {
            System.out.println(node);
            Integer itemId = node.get("classid").asInt();
            if (itemMap.containsKey(itemId)) {
                Item item = itemMap.get(itemId);
                item.setAmount(item.getAmount() + 1);
                itemMap.put(itemId, item);
            } else {
                itemMap.put(itemId, mapper.readValue(node.toString(), Item.class));
            }
        }
        return new ArrayList<>(itemMap.values());
    }

}
