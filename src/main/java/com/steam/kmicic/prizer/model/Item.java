package com.steam.kmicic.prizer.model;

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
    private Integer id;
    private String name;
    private BigDecimal purchasePrice;
    private BigDecimal currentSteamPrice;
    private int quantity;
}
