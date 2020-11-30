package com.steam.kmicic.prizer.domain;

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
public class Item {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("purchasePrice")
    private BigDecimal purchasePrice;
    @JsonProperty("currentSteamPrice")
    private BigDecimal currentSteamPrice;
    @JsonProperty("quantity")
    private int quantity;
}
