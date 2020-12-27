package com.steam.kmicic.prizer.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotNull
    private ApplicationUser applicationUser;
    @NotNull
    private boolean isSteamInventoryList;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "lis")
    private List<Item> items;
}
