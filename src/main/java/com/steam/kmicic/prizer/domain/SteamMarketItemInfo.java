package com.steam.kmicic.prizer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SteamMarketItemInfo {
    private String classId;
    @JsonProperty("lowest_price")
    private String currentPrice;
    @JsonProperty("median_price")
    private String median;
}
