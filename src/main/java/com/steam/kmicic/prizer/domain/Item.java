package com.steam.kmicic.prizer.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @JsonAlias("classid")
    @JsonProperty("class_id")
    private String classId;
    @JsonProperty("app_id")
    @JsonAlias("appid")
    private Integer gameId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("market_hash_name")
    @NotNull
    private String marketHashName;
    @JsonProperty("purchasePrice")
    private BigDecimal purchasePrice;
    @JsonProperty("lowest_price")
    @NotNull
    private BigDecimal currentSteamPrice;
    @JsonProperty("median_price")
    private String medianPrice;
    @JsonProperty("amount")
    @NotNull
    private int amount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "listing_idd")
    private Listing lis;
    private char currencySymbol;
}
