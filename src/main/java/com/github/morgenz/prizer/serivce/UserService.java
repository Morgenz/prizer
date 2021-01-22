package com.github.morgenz.prizer.serivce;

import com.github.morgenz.prizer.domain.ApplicationUser;
import com.github.morgenz.prizer.security.PasswordEncoderConfig;
import com.github.morgenz.prizer.domain.Listing;
import com.github.morgenz.prizer.repository.RoleRepository;
import com.github.morgenz.prizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private ListingService listingService;
    private PasswordEncoderConfig passwordEncoderConfig;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(ListingService listingService, PasswordEncoderConfig passwordEncoderConfig, UserRepository userRepository, RoleRepository roleRepository) {
        this.listingService = listingService;
        this.passwordEncoderConfig = passwordEncoderConfig;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

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
