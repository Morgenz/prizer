package com.steam.kmicic.prizer.serivce;

import com.steam.kmicic.prizer.domain.ApplicationUser;
import com.steam.kmicic.prizer.domain.Listing;
import com.steam.kmicic.prizer.repository.RoleRepository;
import com.steam.kmicic.prizer.repository.UserRepository;
import com.steam.kmicic.prizer.security.PasswordEncoderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private ListingService listingService;

    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void register(ApplicationUser applicationUser) {
        if (applicationUser.getRoles() == null) {
            applicationUser.setRoles(Collections.singletonList(roleRepository.findByRoleName("ROLE_USER")));
        }
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
