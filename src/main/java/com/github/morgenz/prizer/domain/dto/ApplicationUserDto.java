package com.github.morgenz.prizer.domain.dto;

import com.github.morgenz.prizer.domain.entity.Listing;
import com.github.morgenz.prizer.domain.entity.Role;
import com.sun.istack.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class ApplicationUserDto {

    @NotNull
    private Long id;
    @NotNull
    private String username;
    private String steamId;
    private List<Listing> itemLists;
    private List<Role> roles;

}
