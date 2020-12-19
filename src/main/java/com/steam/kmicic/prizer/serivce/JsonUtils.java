package com.steam.kmicic.prizer.serivce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.steam.kmicic.prizer.domain.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class JsonUtils {

    ObjectMapper mapper = new ObjectMapper();
    private static final String DESCRIPTION_NODE_NAME = "descriptions";
    private static final String ASSETS_NODE_NAME = "assets";
    private static final String CLASS_ID_NODE_FIELD = "classid";

    public List<Item> parseSteamInventory(String inventory) throws JsonProcessingException {
        Map<String, Item> itemMap = new HashMap<>();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode rootNode = mapper.readTree(inventory);
        JsonNode firstResult = rootNode.get(DESCRIPTION_NODE_NAME);
        for (JsonNode node : firstResult) {
            itemMap.put(node.get(CLASS_ID_NODE_FIELD).asText(), mapper.readValue(node.toString(), Item.class));
        }
        countInventoryItems(rootNode.get(ASSETS_NODE_NAME), itemMap);
        return new ArrayList<>(itemMap.values());
    }

    private void countInventoryItems(JsonNode assets, Map<String, Item> items) {
        for (JsonNode node : assets) {
            String itemId = node.get(CLASS_ID_NODE_FIELD).asText();
            Item item = items.get(itemId);
            item.setAmount(item.getAmount() + 1);
            items.put(itemId, item);
        }
    }
}
