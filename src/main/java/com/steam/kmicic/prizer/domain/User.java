package com.steam.kmicic.prizer.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @NotNull
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL , mappedBy = "user")
    private List<Listing> itemLists;
}
