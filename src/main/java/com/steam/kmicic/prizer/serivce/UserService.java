package com.steam.kmicic.prizer.serivce;

import com.steam.kmicic.prizer.domain.ApplicationUser;
import com.steam.kmicic.prizer.security.PasswordEncoderConfig;
import com.steam.kmicic.prizer.domain.Listing;
import com.steam.kmicic.prizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private ListingService listingService;

    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;
    @Autowired
    private UserRepository userRepository;

    public void register(ApplicationUser applicationUser) {
        applicationUser.setPassword(passwordEncoderConfig.passwordEncoder().encode(applicationUser.getPassword()));
        userRepository.save(applicationUser);
    }

    public List<ApplicationUser> getAllUsers() {
        return (List<ApplicationUser>) userRepository.findAll();
    }

    public void updateSteamInventoryListing(ApplicationUser applicationUser) {
        applicationUser.getItemLists().stream()
                .filter(Listing::isSteamInventoryList)
                .findAny()
                .ifPresentOrElse(listing -> listingService.updateSteamListing(applicationUser, listing), () -> listingService.addSteamListing(applicationUser));
    }
}
