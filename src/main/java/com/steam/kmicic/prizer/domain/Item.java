package com.steam.kmicic.prizer.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @Id
    @JsonProperty("id")
    @JsonAlias("classid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @JsonProperty("app_id")
    @JsonAlias("appid")
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
    @JsonProperty("amount")
    private int amount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="listing_idd")
    private Listing lis;
}
