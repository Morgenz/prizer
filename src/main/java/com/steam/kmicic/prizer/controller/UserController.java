package com.steam.kmicic.prizer.controller;

import com.steam.kmicic.prizer.domain.ApplicationUser;
import com.steam.kmicic.prizer.repository.UserRepository;
import com.steam.kmicic.prizer.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody ApplicationUser applicationUser) {
        userService.register(applicationUser);
    }

    @GetMapping()
    public List<ApplicationUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/steamInventory")
    public void steamInventory(@Valid @RequestBody ApplicationUser applicationUser) {
        userService.updateSteamInventoryListing(applicationUser);
    }

    @PostMapping("/steamInventory")
    public void steamInventoryUpdate(@Valid @RequestBody ApplicationUser applicationUser) throws NoSuchFieldException {
        if (applicationUser.getSteamId() == null) {
            throw new NoSuchFieldException("STEAM ID FOR USER:" + applicationUser.getUsername() + " IS NOT SET");
        }
        userService.updateSteamInventoryListing(applicationUser);
    }

}
