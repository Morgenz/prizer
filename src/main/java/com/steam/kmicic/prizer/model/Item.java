package com.steam.kmicic.prizer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Entity
public class Item {

    @GeneratedValue
    @Id
    private Integer id;
    private String name;
    private BigDecimal purchasePrice;
    private BigDecimal currentSteamPrice;
    private int quantity;
}
