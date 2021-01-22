package com.github.morgenz.prizer.controller;

import com.github.morgenz.prizer.domain.ApplicationUser;
import com.github.morgenz.prizer.repository.UserRepository;
import com.github.morgenz.prizer.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

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
