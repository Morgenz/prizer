package com.steam.kmicic.prizer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("app_id")
    private Integer gameId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("market_hash_name")
    private String marketHashName;
    @JsonProperty("purchasePrice")
    private BigDecimal purchasePrice;
    @JsonProperty("lowest_price")
    private String currentSteamPrice;
    @JsonProperty("median_price")
    private String medianPrice;
    @JsonProperty("volume")
    private int volume;
    @JsonProperty("quantity")
    private int quantity;
}
